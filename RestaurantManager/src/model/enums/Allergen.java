/*
 * Allergen.java 2026-04-12
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
 * /src/model/enums/Allergen.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk)
 *
 * Lists every recognized allergen the system can handle. Both Dish and Customer
 * hold a Set of these values. AllergenFilter compares the two sets to decide
 * what a customer can safely order.
 *
 * Allergen — Relationships uses ← Dish uses ← Customer
 */
public enum Allergen {
    GLUTEN("Gluten", "Contains wheat, barley, rye, or their derivatives"),
    LACTOSE("Lactose", "Contains dairy products"),
    NUTS("Nuts", "Contains tree nuts or peanuts"),
    SHELLFISH("Shellfish", "Contains crustaceans like shrimp, crab, lobster"),
    EGG("Egg", "Contains eggs or egg products"),
    SOY("Soy", "Contains soybeans or soy derivatives"),
    FISH("Fish", "Contains fish or fish derivatives");

    // === Fields ===
    private final String displayName;
    private final String description;

    // === Constructor ===
    private Allergen(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    // === Getters ===
    /**
     * Gets the user-facing allergen name.
     *
     * @return Display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the allergen description.
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
