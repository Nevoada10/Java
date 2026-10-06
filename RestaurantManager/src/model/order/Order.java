/*
 * Order.java 2026-04-12
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
package model.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.enums.OrderStatus;
import model.table.Table;

/**
 * /src/model/order/Order.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         Represents a live table order from the moment it is opened until it
 *         is paid.
 *         Holds a list of OrderItems and tracks its own status to prevent
 *         illegal
 *         modifications. The Waiter creates it, the customer's allergens shape
 *         its
 *         contents, and markPaid() locks it permanently.
 *
 *         uses → OrderStatus: Tracks the order's lifecycle. composition →
 *         OrderItem: An
 *         order cannot exist without its items. association → Table: Each order
 *         is
 *         linked to a specific table.
 */
public class Order {

    private String id;
    private String reservationId;
    private Table table;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime createdAt;
    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    /**
     * Creates a new open order linked to a reservation and table.
     *
     * @param reservationId Reservation identifier associated with this order.
     * @param table         Table where the order is being served.
     * @param items         Initial list of order items.
     */
    public Order(String reservationId, Table table, List<OrderItem> items) {
        this.id = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.reservationId = reservationId;
        this.table = table;
        this.items = items;
        this.status = OrderStatus.OPEN;
        this.createdAt = LocalDateTime.now();

    }

    /**
     * Reconstructs an existing order with explicit persistence fields.
     *
     * @param reservationId Reservation identifier associated with this order.
     * @param id            Order identifier.
     * @param table         Table where the order is being served.
     * @param items         List of order items.
     * @param status        Current order status.
     * @param createdAt     Creation timestamp.
     */
    public Order(String reservationId, String id, Table table, List<OrderItem> items, OrderStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.reservationId = reservationId;
        this.table = table;
        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
    }
    // -------------------------------------------------------------------------
    // Business methods (from diagram)
    // -------------------------------------------------------------------------

    /**
     * Adds an item to the order.
     * Throws IllegalStateException if the order is already CLOSED or PAID.
     *
     * @param item the OrderItem to add
     */
    public void addItem(OrderItem item) {

        if (!isModifiable()) {
            throw new IllegalStateException(
                    "Cannot add items to an order with status: " + status);
        }
        items.add(item);
    }

    /**
     * Calculates and returns the total price of all items in the order.
     *
     * @return the sum of all OrderItem subtotals
     */
    public double getTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    /**
     * Marks the order as PAID, locking it permanently against modifications.
     * Throws IllegalStateException if the order is not in OPEN status.
     */
    public void markPaid() {
        if (!isModifiable()) {
            throw new IllegalStateException(
                    "Only OPEN orders can be marked as paid. Current status: " + status);
        }
        this.status = OrderStatus.PAID;
    }

    /**
     * Returns whether the order can still be modified (items added/removed).
     * Only OPEN orders are modifiable.
     *
     * @return true if status is OPEN
     */
    public boolean isModifiable() {
        return status == OrderStatus.OPEN;
    }

    // -------------------------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------------------------

    /**
     * Gets the unique order identifier.
     *
     * @return Order identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the table associated with this order.
     *
     * @return Associated table.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Gets the reservation identifier linked to this order.
     *
     * @return Reservation identifier.
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Sets the reservation identifier linked to this order.
     *
     * @param reservationId Reservation identifier to assign.
     */
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Gets a defensive copy of this order's items.
     *
     * @return Copy of current order items.
     */
    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Gets the current order status.
     *
     * @return Current status.
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Gets the order creation timestamp.
     *
     * @return Creation date-time.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    /**
     * Builds a compact textual representation of the order for console output.
     *
     * @return Formatted order summary.
     */
    @Override
    public String toString() {
        String itemsList = "";
        for (OrderItem item : items) {
            if (!itemsList.isEmpty())
                itemsList += ", ";
            if (item.isDish()) {
                itemsList += item.getDish().getName() + " (x" + item.getQuantity() + ")";
            } else {
                itemsList += "Menu: " + item.getMenu().getName() + " (x" + item.getQuantity() + ")";
            }
        }
        if (itemsList.isEmpty()) {
            itemsList = "Empty";
        }
        return String.format("%-12s │ Table: %-3s │ Status: %-8s │ Total: %6.2f € │ Items: %s",
                id, (table != null ? table.getId() : "N/A"), status.getDisplayName(), getTotal(), itemsList);
    }
}
