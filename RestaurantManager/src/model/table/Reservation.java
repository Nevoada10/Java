/*
 * Reservation.java 2026-04-10
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
package model.table;

import java.time.LocalDate;
import java.util.UUID;

import model.enums.ReservationStatus;
import model.user.Customer;

/**
 * src/model/table/Reservation.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         Records a Customer's claim on a specific Table during a specific
 *         Shift on a
 *         given date. Created by ReservationManager and stored in the
 *         Customer's
 *         reservation list. cancel() frees the table slot so another customer
 *         can book
 *         it.
 *
 *         Reservation — Relationships association → Customer: Links the
 *         reservation to
 *         the customer. association → Table: Links the reservation to the
 *         table.
 *         association → Shift: Links the reservation to the shift. composition
 *         ←
 *         ReservationManager: The reservation manager creates and manages
 *         reservations.
 */
public class Reservation {

    private String id;
    private Customer customer;
    private Table table;
    private int numSeats;
    private Shift shift;
    private LocalDate date;
    private ReservationStatus status;

    /**
     * Creates a new reservation in PENDING state.
     *
     * @param customer Customer who owns the reservation.
     * @param table    Reserved table.
     * @param numSeats Number of seats requested.
     * @param shift    Reserved shift.
     * @param date     Reserved date.
     */
    public Reservation(Customer customer, Table table, int numSeats, Shift shift, LocalDate date) {
        this.id = "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.customer = customer;
        this.table = table;
        this.numSeats = numSeats;
        this.shift = shift;
        this.date = date;
        this.status = ReservationStatus.PENDING;
    }

    /**
     * Gets the reservation identifier.
     *
     * @return Reservation id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the reservation identifier.
     *
     * @param id New reservation id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the customer linked to this reservation.
     *
     * @return Customer who owns the reservation.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the table assigned to this reservation.
     *
     * @return Reserved table.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Sets the table assigned to this reservation.
     *
     * @param table Table to assign.
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Gets the number of reserved seats.
     *
     * @return Number of seats.
     */
    public int getNumSeats() {
        return numSeats;
    }

    /**
     * Gets the reserved shift.
     *
     * @return Shift associated with this reservation.
     */
    public Shift getShift() {
        return shift;
    }

    /**
     * Gets the reserved date.
     *
     * @return Reservation date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the current reservation status.
     *
     * @return Current status.
     */
    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * Sets status directly when rebuilding a reservation from persisted state.
     * This bypasses transition guards used by runtime business operations.
     *
     * @param status Persisted reservation status.
     */
    public void setStatusFromPersistence(ReservationStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    /**
     * Returns whether the reservation state can still be modified.
     * Only PENDING reservations are modifiable (can change their state).
     * Once ACTIVE or in any terminal state, the reservation is locked.
     * Note: Orders can still be created/modified in ACTIVE reservations separately.
     *
     * @return true if status is PENDING
     */
    public boolean isModifiable() {
        return status == ReservationStatus.PENDING;
    }

    /**
     * Cancels this reservation when still modifiable.
     *
     * @return {@code true} when status changed to CANCELLED.
     */
    public boolean cancel() {
        if (!isModifiable()) {
            return false;
        }
        status = ReservationStatus.CANCELLED;
        return true;
    }

    /**
     * Confirms this reservation when still modifiable.
     *
     * @return {@code true} when status changed to ACTIVE.
     */
    public boolean confirm() {
        if (!isModifiable()) {
            return false;
        }
        status = ReservationStatus.ACTIVE;
        return true;
    }

    /**
     * Expires this reservation when still modifiable.
     *
     * @return {@code true} when status changed to EXPIRED.
     */
    public boolean expire() {
        if (!isModifiable()) {
            return false;
        }
        status = ReservationStatus.EXPIRED;
        return true;
    }

    /**
     * Returns a string representation of this reservation.
     *
     * @return a string representation of the reservation
     */
    @Override
    public String toString() {
        return String.format("Res: %s | Customer: %s (%s) | Table: %s (Seats: %d) | Date: %s (%s) | Status: %s",
                id, customer.getName(), customer.getId(), table.getId(), numSeats, date, shift, status.getDisplayName());
    }
}
