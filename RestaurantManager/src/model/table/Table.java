/*
 * Table.java 2026-04-10
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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * src/model/table/Table.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         A physical table in the restaurant with a fixed capacity. Tracks
 *         which
 *         Customer is currently assigned so the Waiter knows where to bring
 *         orders.
 *         isAvailable() is checked by ReservationManager before accepting a new
 *         booking.
 *
 *         Table — Relationships association ← Order: Links the table to the
 *         order.
 *         association ← Waiter: Links the table to the waiter. association ←
 *         Reservation: Links the table to the reservation.
 */
public class Table {

    private String id;
    private int capacity;
    private Set<Shift> bookedShifts;
    private static final AtomicInteger idNext = new AtomicInteger(1);

    /**
     * Creates a table with the provided seating capacity.
     *
     * @param capacity Number of seats available at the table.
     */
    public Table(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be positive.");
        }
        this.id = String.format("T%d", idNext.getAndIncrement());
        this.capacity = capacity;
        this.bookedShifts = new HashSet<>();
    }

    /**
     * Gets the table identifier.
     *
     * @return Table id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the table identifier.
     *
     * @param id New table id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the seating capacity of the table.
     *
     * @return Number of seats.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Checks whether this table can seat the requested number of guests.
     *
     * @param numSeats Requested seats.
     * @return {@code true} when capacity is sufficient.
     */
    public boolean canAccommodate(int numSeats) {
        return numSeats <= capacity;
    }

    /**
     * Checks table availability for a party size and shift.
     *
     * @param numSeats Requested seats.
     * @param shift    Requested shift.
     * @return {@code true} when capacity is sufficient and shift is not booked.
     */
    private boolean isAvailable(int numSeats, Shift shift) {
        return canAccommodate(numSeats) && !bookedShifts.contains(shift);
    }

    /**
     * Attempts to reserve this table for the provided shift.
     *
     * @param numSeats Requested seats.
     * @param shift    Shift to reserve.
     * @return {@code true} when the booking is accepted.
     */
    public boolean bookShift(int numSeats, Shift shift) {
        if (isAvailable(numSeats, shift)) {
            bookedShifts.add(shift);
            return true;
        }

        return false;
    }

    /**
     * Removes a previously booked shift.
     *
     * @param shift Shift to remove from bookings.
     * @return {@code true} when the shift was present and removed.
     */
    public boolean removeBookedShift(Shift shift) {
        return bookedShifts.remove(shift);
    }

    /**
     * Resets the next generated table id value.
     *
     * @param value Next numeric value used for table ids.
     */
    public static void setNextId(int value) {
        idNext.set(value);
    }

    /**
     * Builds a readable table summary.
     *
     * @return Formatted table details.
     */
    @Override
    public String toString() {
        return String.format("Table %s (Capacity: %d) | Booked: %s", id, capacity, bookedShifts);
    }

    /**
     * Compares tables by identifier.
     *
     * @param o Object to compare.
     * @return {@code true} when both objects represent the same table id.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Table other)) {
            return false;
        }
        return id.equals(other.id);
    }

    /**
     * Returns a hash code consistent with {@link #equals(Object)}.
     *
     * @return Hash code based on table id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
