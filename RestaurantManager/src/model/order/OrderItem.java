/*
 * OrderItem.java 2026-04-12
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.dish.Dish;
import model.menu.Menu;

/**
 * src/model/order/OrderItem.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk)
 *
 *         A single line inside an Order — one Dish at a given quantity with an
 *         optional
 *         supplement. Exists only as part of an Order (composition), so it is
 *         deleted
 *         when the order is removed. getSubtotal() multiplies dish price by
 *         quantity
 *         for the bill calculation.
 *
 *         OrderItem — Relationships composition ← Order: An order item cannot
 *         exist
 *         without an order. association → Dish: Each order item references a
 *         dish.
 */
public class OrderItem {

    private final Dish dish;
    private final Menu menu;
    private final List<Dish> chosenCourses; // Used only when this item represents a menu.
    private final int quantity;
    private final String supplement;

    /**
     * Creates an order item for a single dish.
     *
     * @param dish       Dish associated with this item.
     * @param quantity   Number of units requested.
     * @param supplement Optional note for preparation or service.
     */
    public OrderItem(Dish dish, int quantity, String supplement) {
        this.dish = dish;
        this.menu = null;
        this.chosenCourses = Collections.emptyList();
        this.quantity = quantity;
        this.supplement = supplement;
    }

    /**
     * Creates an order item for a menu without selected courses yet.
     *
     * @param menu       Menu associated with this item.
     * @param quantity   Number of units requested.
     * @param supplement Optional note for preparation or service.
     */
    public OrderItem(Menu menu, int quantity, String supplement) {
        this.dish = null;
        this.menu = menu;
        this.chosenCourses = new ArrayList<>();
        this.quantity = quantity;
        this.supplement = supplement;
    }

    /**
     * Creates an order item for a menu with chosen courses.
     *
     * @param menu          Menu associated with this item.
     * @param chosenCourses Courses selected for the menu.
     * @param quantity      Number of units requested.
     * @param supplement    Optional note for preparation or service.
     */
    public OrderItem(Menu menu, List<Dish> chosenCourses, int quantity, String supplement) {
        this.dish = null;
        this.menu = menu;
        this.chosenCourses = new ArrayList<>(chosenCourses);
        this.quantity = quantity;
        this.supplement = supplement;
    }

    /**
     * Calculates the subtotal for this order item.
     *
     * @return The subtotal (dish price * quantity).
     */
    public double getSubtotal() {
        if (dish != null)
            return dish.getPrice() * quantity;
        if (menu != null)
            return menu.getPrice() * quantity;
        return 0;
    }

    /**
     * Indicates whether this item refers to a single dish.
     *
     * @return {@code true} when the item is dish-based.
     */
    public boolean isDish() {
        return dish != null;
    }

    /**
     * Indicates whether this item refers to a menu.
     *
     * @return {@code true} when the item is menu-based.
     */
    public boolean isMenu() {
        return menu != null;
    }

    /**
     * Gets the dish associated with this item.
     *
     * @return Dish instance, or {@code null} when this is a menu item.
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Gets the menu associated with this item.
     *
     * @return Menu instance, or {@code null} when this is a dish item.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Returns the selected menu courses.
     *
     * @return Unmodifiable list of chosen courses.
     */
    public List<Dish> getChosenCourses() {
        return Collections.unmodifiableList(chosenCourses);
    }

    /**
     * Gets the quantity requested for this item.
     *
     * @return Requested quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the optional supplement note.
     *
     * @return Supplement text, potentially blank or {@code null}.
     */
    public String getSupplement() {
        return supplement;
    }

    /**
     * Builds a user-facing line representation of the order item.
     *
     * @return Formatted item description including subtotal.
     */
    @Override
    public String toString() {
        String name = isDish() ? dish.getName() : "Menu: " + menu.getName();
        String supp = (supplement == null || supplement.isBlank()) ? "" : " (Note: " + supplement + ")";
        if (isMenu() && !chosenCourses.isEmpty()) {
            List<String> courseNames = new ArrayList<>();
            for (Dish d : chosenCourses) {
                courseNames.add(d.getName());
            }
            name += " " + courseNames;
        }
        return String.format("%s x %d%s - Subtotal: %.2f €", name, quantity, supp, getSubtotal());
    }
}