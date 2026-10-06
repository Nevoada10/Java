/*
 * Role.java 2026-04-12
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
 * src/model/enums/Role.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk)
 *
 * Defines the three possible user types (MANAGER, WAITER, CUSTOMER) in the
 * system. Used by User to determine which text menu is shown at login. Keeps
 * role logic centralized as a single source of truth.
 *
 * Role — Relationships uses ← User: The User class uses this enum to determine
 * which menu to display.
 */
public enum Role {
    MANAGER("Manager", "System administrator with full access", "MGR"),
    WAITER("Waiter", "Restaurant staff serving customers", "WAIT"),
    CUSTOMER("Customer", "Restaurant customer", "CUS");

    // === Fields ===
    private final String displayName;
    private final String description;
    private final String prefix;

    // === Constructor ===
    private Role(String displayName, String description, String prefix) {
        this.displayName = displayName;
        this.description = description;
        this.prefix = prefix;
    }

    // === Getters ===
    /**
     * Gets the user-facing role name.
     *
     * @return Display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the role description.
     *
     * @return Description text.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the short role prefix.
     *
     * @return Role prefix.
     */
    public String getPrefix() {
        return prefix;
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
