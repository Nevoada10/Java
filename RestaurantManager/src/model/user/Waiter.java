/*
 * Waiter.java 2026-04-09
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

import model.enums.Role;

/**
 * src/model/user/Waiter.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 * Represents a waiter in the system. Extends User with role fixed to
 * Role.WAITER.
 *
 * Waiter — Relationships extends → User
 */
public class Waiter extends User {

    /**
     * Creates a waiter with role fixed to {@link Role#WAITER}.
     *
     * @param id       Waiter uid.
     * @param name     Waiter name.
     * @param password Waiter password/hash.
     * @param role     Ignored parameter kept for API compatibility.
     */
    public Waiter(String id, String name, String password, Role role) {
        super(id, name, password, Role.WAITER);
    }

    /**
     * Returns the inherited user representation.
     *
     * @return Waiter text representation.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
