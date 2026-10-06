/*
 * OrderStatus.java 2026-04-12
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
 * /src/model/enums/OrderStatus.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk)
 *
 *         Tracks the lifecycle of an Order from creation to payment. Prevents
 *         waiters
 *         from modifying a closed or paid order. Drives the isModifiable()
 *         guard inside
 *         Order.
 *
 *         OPEN: is alive and editable. The waiter can add items, remove items,
 *         change
 *         quantities, change price. CLOSED: is alive and not editable. The
 *         waiter gives
 *         the paper to the cashier, no more edits. PAID: is not alive and not
 *         editable.
 *
 *         OrderStatus — Relationships uses ← Order: The Order class uses this
 *         enum to
 *         track its status.
 */
public enum OrderStatus {
    OPEN("Open", "The order is open"),
    CANCELLED("Cancelled", "The order is cancelled"),
    PAID("Paid", "The order is paid");

    // === Fields ===
    private final String displayName;
    private final String description;

    // === Constructor ===
    private OrderStatus(String displayName, String description) {
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
