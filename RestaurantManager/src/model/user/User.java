/*
 * User.java 2026-04-09
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
 * src/model/user/User.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         Common blueprint for every person interacting with the system. Holds
 *         shared
 *         identity ids. Forces subclasses to inherit role-based behavior
 *         without
 *         duplicating code.
 *
 *         User — Relationships extends ← Manager extends ← Waiter extends ←
 *         Customer
 *         uses → Role
 */
public class User {

    // === Attributes ===
    private final String id;
    private final String name;
    private final String password;
    private final Role role;

    // === Constructor ===
    /**
     * Constructor for User.
     *
     * @param id       the unique identifier for the user
     * @param name     the name of the user
     * @param password the password for the user
     * @param role     the role of the user
     */
    public User(String id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // === Getters ===
    /**
     * Gets the user id.
     *
     * @return User id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the user display name.
     *
     * @return User name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user role.
     *
     * @return User role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets the stored user password value.
     *
     * @return Password/hash value.
     */
    public String getPassword() {
        return password;
    }

    // === toString() ===
    /**
     * Builds a compact string representation of the user.
     *
     * @return User text representation.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (ID: %s)", role, name, id);
    }

}
