/*
 * Customer.java 2026-04-09
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
package model.user;

import java.util.Set;
import model.enums.Allergen;
import model.enums.Role;

/**
 * src/model/user/Customer.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         Represents a customer in the system. Extends User with role fixed to
 *         Role.CUSTOMER.
 *
 *         Customer — Relationships extends → User
 */
public class Customer extends User {

    private final Set<Allergen> allergens;

    /**
     * Creates a customer with role fixed to {@link Role#CUSTOMER}.
     *
     * @param id        Customer uid.
     * @param name      Customer name.
     * @param password  Customer password/hash.
     * @param allergens Customer allergy set.
     */
    public Customer(String id, String name, String password, Set<Allergen> allergens) {
        super(id, name, password, Role.CUSTOMER);
        this.allergens = allergens;
    }

    /**
     * Gets the customer allergen set.
     *
     * @return Customer allergens.
     */
    public Set<Allergen> getAllergens() {
        return allergens;
    }

    /**
     * Builds a string representation including customer allergy summary.
     *
     * @return Customer text representation.
     */
    @Override
    public String toString() {
        String allergenList = allergens.isEmpty() ? "None" : allergens.toString();
        return super.toString() + " | Allergens: " + allergenList;
    }
}
