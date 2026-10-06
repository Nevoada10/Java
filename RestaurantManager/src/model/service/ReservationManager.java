/*
 * ReservationManager.java 2026-04-12
 *
 * Copyright 2026 Carles Conesa Mañosa, Uriel Neves Silva, Kadiatou Diallo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.dish.Dish;
import model.enums.ReservationStatus;
import model.menu.Menu;
import model.table.Reservation;
import model.table.Shift;
import model.table.Table;
import model.user.User;
import jdbc.JdbcDishRepository;
import jdbc.JdbcMenusRepository;
import jdbc.JdbcOrdersRepository;
import jdbc.JdbcReservationsRepository;
import jdbc.JdbcTablesRepository;

/**
 * /src/model/service/ReservationManager.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         ReservationManager coordinates the full booking lifecycle: checking
 *         availability, creating Reservations, and cancelling them. Uses
 *         Shift.overlaps() internally to prevent a table from being
 *         double-booked in
 *         the same time window. Both Manager and Customer interact with it, but
 *         all
 *         booking rules live here.
 *
 *         ReservationManager — Relationships composition → Reservation
 */
public class ReservationManager {

    /**
     * Result states for reservation lifecycle operations.
     */
    public enum ReservationResult {
        SUCCESS,
        INVALID_RESERVATION,
        NO_CAPACITY,
        PAST_DATE,
        SHIFT_CONFLICT,
        NOT_FOUND,
        NOT_MODIFIABLE,
        PERSISTENCE_ERROR
    }

    private List<Reservation> reservations;
    private List<Table> tables;
    private List<Dish> dishes;
    private List<Menu> menus;

    private JdbcTablesRepository tableRepo = new JdbcTablesRepository();
    private JdbcReservationsRepository resRepo = new JdbcReservationsRepository();
    private final LdapUserRepositoryProduction repo = new LdapUserRepositoryProduction();
    private JdbcOrdersRepository ordersRepo = new JdbcOrdersRepository();
    private JdbcDishRepository dishRepo = new JdbcDishRepository();
    private JdbcMenusRepository menuRepo = new JdbcMenusRepository();

    /**
     * Creates the reservation manager and loads current data from persistence.
     */
    public ReservationManager() {
        this.tables = tableRepo.loadAll();
        this.reservations = resRepo.loadAll(tables);

        this.dishes = dishRepo.findAll();
        this.menus = menuRepo.loadAll(dishes);
        ordersRepo.loadAll(tables, dishes, menus);

        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.PENDING) {
                for (Table t : tables) {
                    if (t.getId().equals(r.getTable().getId())) {
                        t.bookShift(t.getCapacity(), r.getShift());
                        break;
                    }
                }
            }
        }
    }

    /**
     * Gets the current managed table list.
     *
     * @return Internal list of tables.
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * Adds a new reservation after validating booking constraints.
     *
     * @param reservation Reservation request to add.
     * @return Descriptive operation result.
     */
    public ReservationResult addReservation(Reservation reservation) {

        if (reservation == null || reservation.getTable() == null || reservation.getDate() == null
                || reservation.getShift() == null) {
            return ReservationResult.INVALID_RESERVATION;
        }

        if (!reservation.getTable().canAccommodate(reservation.getNumSeats())) {
            return ReservationResult.NO_CAPACITY;
        }

        if (reservation.getDate().isBefore(LocalDate.now())) {
            return ReservationResult.PAST_DATE;
        }

        for (Reservation r : reservations) {
            if (r.getTable().equals(reservation.getTable())
                    && r.getDate().equals(reservation.getDate())
                    && r.getStatus() != ReservationStatus.CANCELLED
                    && r.getShift().overlaps(reservation.getShift())) {
                return ReservationResult.SHIFT_CONFLICT;
            }
        }
        reservations.add(reservation);
        reservation.getTable().bookShift(reservation.getTable().getCapacity(), reservation.getShift());

        resRepo.saveAll(reservations);
        return ReservationResult.SUCCESS;
    }

    /**
     * Cancels an existing reservation by identifier.
     *
     * @param id Reservation id.
     * @return Descriptive operation result.
     */
    public ReservationResult cancelReservation(String id) {
        if (id == null || id.isBlank()) {
            return ReservationResult.NOT_FOUND;
        }

        for (Reservation r : reservations) {

            if (r.getId().equals(id)) {
                if (!r.isModifiable()) {
                    return ReservationResult.NOT_MODIFIABLE;
                }

                boolean persisted = resRepo.updateReservationStatus(id, ReservationStatus.CANCELLED);
                if (!persisted) {
                    return ReservationResult.PERSISTENCE_ERROR;
                }

                boolean cancelled = r.cancel();
                if (!cancelled) {
                    return ReservationResult.NOT_MODIFIABLE;
                }

                for (Table t : tables) {
                    if (t.getId().equals(r.getTable().getId())) {
                        t.removeBookedShift(r.getShift());
                        break;
                    }
                }
                return ReservationResult.SUCCESS;
            }
        }
        return ReservationResult.NOT_FOUND;
    }

    /**
     * Gets tables that can host a party for the given shift and date.
     *
     * @param shift Shift requested.
     * @param date  Date requested.
     * @param seats Required number of seats.
     * @return Tables currently available for the request.
     */
    public List<Table> getAvailableTables(Shift shift, LocalDate date, int seats) {
        List<Table> availableTables = new ArrayList<>();

        for (Table table : tables) {

            if (!table.canAccommodate(seats)) {
                continue;
            }

            boolean occupied = false;

            for (Reservation r : reservations) {

                if (r.getTable().equals(table)
                        && r.getDate().equals(date)
                        && r.getStatus() != ReservationStatus.CANCELLED
                        && r.getShift().overlaps(shift)) {

                    occupied = true;
                    break;
                }
            }

            if (!occupied) {
                availableTables.add(table);
            }
        }

        return availableTables;
    }

    /**
     * Persists current reservation state.
     */
    public void saveReservations() {
        resRepo.saveAll(reservations);
    }

    /**
     * Gets the internal reservation list.
     *
     * @return Internal reservation list reference.
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Lists reservations as a defensive copy.
     *
     * @return Copy of reservations.
     */
    public List<Reservation> listReservations() {
        return new ArrayList<>(reservations);
    }

    /**
     * Lists tables as a defensive copy.
     *
     * @return Copy of tables.
     */
    public List<Table> listTables() {
        return new ArrayList<>(tables);
    }

    /**
     * Allows the manager to request the addition of a new user.
     * 
     * @param user the object containing the details of the user to be added.
     * @return true if the user was successfully added, false if an error occurs
     *         during the process.
     */
    public boolean addUser(User user) {
        if (user == null) {
            return false;
        }

        // Delegate to repository for persistence
        return repo.saveUser(user);
    }

    /**
     * Allows the manager to request the removal of an existing user.
     * 
     * @param userId the unique identifier of the user to be removed.
     * @return true if the user was successfully removed, false if validation fails
     *         or deletion fails.
     */
    public boolean removeUser(String userId) {
        if (userId == null || userId.isBlank()) {
            return false;
        }

        // Delegate to repository for persistence
        return repo.deleteUser(userId);

    }

    /**
     * Lists LDAP users as a defensive copy.
     *
     * @return Copy of users stored in LDAP.
     */
    public List<User> listUsers() {
        return new ArrayList<>(repo.findAll());
    }

    /**
     * Computes accumulated revenue from all PAID orders created TODAY.
     * Queries the database for the exact sum: DATE(created_at) = TODAY and status = PAID.
     *
     * @return Total revenue amount from today's paid orders only.
     */
    public double getDailyRevenue() {
        return ordersRepo.getDailyRevenue();
    }
}