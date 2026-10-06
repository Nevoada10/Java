/*
 * Dish.java 2026-04-12
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
package model.dish;

import java.util.Collections;
import java.util.Set;
import model.enums.Allergen;
import model.enums.Category;

/**
 * src/model/dish/Dish.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk) Represents a dish in
 *         the restaurant with its name, price, category, and allergens.
 *
 *         Dish — Relationships - uses → Allergen - association ← OrderItem -
 *         uses ←
 *         AllergenFilter - association ← MenuBase - uses ← DishRepository
 *
 *         schema
 */
public class Dish {

    // === Attributes ===
    private final String name;
    private final double price;
    private final Set<Allergen> allergens;
    private final Category category;

    // === Constructor ===
    /**
     * Creates a dish with immutable core attributes.
     *
     * @param name      Dish name.
     * @param price     Dish price.
     * @param allergens Dish allergen set.
     * @param category  Dish category.
     */
    public Dish(String name, double price, Set<Allergen> allergens, Category category) {
        this.name = name;
        this.price = price;
        this.allergens = allergens;
        this.category = category;
    }

    // === Getters ===
    /**
     * Gets the dish name.
     *
     * @return Dish name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the dish price.
     *
     * @return Dish price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the dish category.
     *
     * @return Dish category.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Returns a read-only view of allergens to protect internal state.
     *
     * @return Unmodifiable allergen set.
     */
    public Set<Allergen> getAllergens() {

        /*
         * Returning an unmodifiable set avoids accidental external mutation of the
         * dish allergen data.
         */
        return Collections.unmodifiableSet(allergens);
    }

    /**
     * Checks if this dish contains the specified allergen.
     *
     * @param allergen the allergen to check for
     * @return true if the dish contains the allergen, false otherwise
     */
    public boolean containsAllergen(Allergen allergen) {
        return allergens.contains(allergen);
    }

    // === toString ===
    /**
     * Builds a formatted string representation for menu/table rendering.
     *
     * @return Formatted dish row.
     */
    @Override
    public String toString() {
        String allergenList = "None";
        if (!allergens.isEmpty()) {
            allergenList = "";
            for (Allergen a : allergens) {
                if (!allergenList.isEmpty()) {
                    allergenList += ", ";
                }
                allergenList += a.getDisplayName();
            }
        }
        return String.format("%-24s │ %-10s │ %6.2f € │ Allergens: %s", name, category.getDisplayName(), price, allergenList);
    }
}