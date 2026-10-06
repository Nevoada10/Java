/*
 * Shift.java 2026-04-10
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

import java.time.LocalTime;
import java.util.Objects;

/**
 * src/model/table/Shift.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk) Represents a named
 *         time window (e.g. lunch 13:00–15:30, dinner 20:00–23:00). The same
 *         table can
 *         be reserved in different Shifts on the same day without conflict.
 *         overlaps()
 *         lets ReservationManager detect double-bookings before they happen.
 *
 *         Shift — Relationships - association ← Reservation
 */
public class Shift {

    // === Attributes ===
    private final String id;
    private final LocalTime startTime;
    private final LocalTime endTime;

    // === Constructor ===
    private Shift(String id, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // === Getters ===
    /**
     * Gets the shift identifier.
     *
     * @return Shift id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the shift start time.
     *
     * @return Start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the shift end time.
     *
     * @return End time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /** First lunch shift (12:00-14:00). */
    public static final Shift LUNCH1 = new Shift("LUNCH_1", LocalTime.of(12, 0), LocalTime.of(14, 0));

    /** Second lunch shift (14:00-16:00). */
    public static final Shift LUNCH2 = new Shift("LUNCH_2", LocalTime.of(14, 0), LocalTime.of(16, 0));

    /** First dinner shift (19:00-21:00). */
    public static final Shift DINNER1 = new Shift("DINNER_1", LocalTime.of(19, 0), LocalTime.of(21, 0));

    /** Second dinner shift (21:00-23:00). */
    public static final Shift DINNER2 = new Shift("DINNER_2", LocalTime.of(21, 0), LocalTime.of(23, 0));

    /**
     * Checks whether this shift overlaps another shift.
     *
     * @param other Shift to compare against.
     * @return {@code true} when the two time ranges intersect.
     */
    public boolean overlaps(Shift other) {
        return startTime.isBefore(other.endTime) && endTime.isAfter(other.startTime);
    }

    // === toString ===
    /**
     * Builds a readable representation of the shift.
     *
     * @return Shift id and time range.
     */
    @Override
    public String toString() {
        return getId() + " (" + getStartTime() + " - " + getEndTime() + ")";
    }

    /**
     * Compares shifts by identifier.
     *
     * @param o Object to compare.
     * @return {@code true} when both objects represent the same shift id.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shift other)) {
            return false;
        }
        return id.equals(other.id);
    }

    /**
     * Returns a hash code consistent with {@link #equals(Object)}.
     *
     * @return Hash code based on shift id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
