/*
 * MenuBase.java 2026-04-12
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
package model.menu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.dish.Dish;
import model.enums.Allergen;

/**
 * @finished true
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk)
 *
 *         Defines the shared structure of any fixed menu: an id, a price, and
 *         an
 *         ordered list of Dish courses. Subclasses enforce their own course
 *         composition
 *         rules via their constructors. Exposes getTotalAllergens() so the UI
 *         can warn
 *         customers before they order.
 *
 *         MenuBase — Relationships extends ← Menu1 extends ← Menu2 extends ←
 *         Menu3
 *         association → Dish
 */
public class Menu {

    // === Attributes ===
    protected String name;
    protected double price;
    protected List<Dish> courses;

    // === Constructor ===
    /**
     * Creates a menu with name, price, and predefined course list.
     *
     * @param name    Menu identifier/name.
     * @param price   Menu price.
     * @param courses Dishes included in the menu.
     */
    public Menu(String name, double price, List<Dish> courses) {
        this.name = name;
        this.price = price;
        this.courses = courses;
    }

    // === Methods ===
    /**
     * Gets the menu name.
     *
     * @return Menu name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the menu price.
     *
     * @return Menu price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the list of dishes that make up the menu.
     *
     * @return Menu courses.
     */
    public List<Dish> getCourses() {
        return courses;
    }

    /**
     * Returns the total set(no duplicates) of allergens that the menu contains.
     * Example: If the menu contains a dish with gluten and another with nuts,
     * the set should return a set with gluten and nuts. Format example:
     * [GLUTEN, NUTS]
     *
     * @return A set of allergens
     */
    public Set<Allergen> getTotalAllergens() {

        /*
         * A set naturally removes duplicates while aggregating allergens from all
         * courses.
         */
        Set<Allergen> allergens = new HashSet<>();
        for (Dish dish : courses) {
            allergens.addAll(dish.getAllergens());
        }

        return allergens;

    }

    /**
     * Builds a formatted string representation for menu/table rendering.
     *
     * @return Formatted menu row.
     */
    @Override
    public String toString() {
        String allergenList = getTotalAllergens().isEmpty() ? "None" : getTotalAllergens().toString();
        return String.format("%-24s │ %-10s │ %6.2f € │ Courses: %d │ Allergens: %s",
                name, "Menu", price, courses.size(), allergenList);
    }
}
