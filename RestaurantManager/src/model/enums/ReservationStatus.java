/*
 * ReservationStatus.java 2026-04-12
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
package model.enums;

/**
 * /src/model/enums/ReservationStatus.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk)
 *
 *         Represents the different states a reservation can be in. A
 *         reservation starts
 *         as pending, meaning it has been booked but not yet active. Once the
 *         manager
 *         has confirmed the reservation, it becomes active. Only active
 *         reservations
 *         can be modified or cancelled.
 *
 *
 *         ReservationStatus — Relationships uses ← Reservation
 *
 */
public enum ReservationStatus {
    PENDING("Pending", "Booked but not yet active"),
    ACTIVE("Active", "Currently occupying the table"),
    CANCELLED("Cancelled", "Cancelled by the customer or manager"),
    COMPLETED("Completed", "Finished, table freed"),
    FRAUDULENT("Fraudulent", "The customer has not paid"),
    EXPIRED("Expired", "No-show: reservation window passed without any order");

    // === Fields ===
    private final String displayName;
    private final String description;

    // === Constructor ===
    private ReservationStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    // === Getters ===
    /**
     * Gets the user-facing status name.
     *
     * @return Display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the status description.
     *
     * @return Description text.
     */
    public String getDescription() {
        return description;
    }

    // === toString ===
    /**
     * Returns the display name for user-facing output.
     *
     * @return Display name.
     */
    @Override
    public String toString() {
        return getDisplayName();
    }
}
