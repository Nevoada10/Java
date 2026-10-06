/*
 * Manager.java 2026-04-09
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

import java.util.ArrayList;
import java.util.List;
import jdbc.JdbcDishRepository;
import model.dish.Dish;
import model.enums.Role;

/**
 * src/model/user/Manager.java
 *
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 *
 *         Represents a manager in the system. Extends User with role fixed to
 *         Role.MANAGER.
 *
 *         Manager — Relationships extends → User
 */
public class Manager extends User {

    private static final JdbcDishRepository dishRepo = new JdbcDishRepository();

    /**
     * Creates a manager domain user.
     *
     * @param id       Unique manager identifier.
     * @param name     Manager display name.
     * @param password Manager password hash/plain value used by the caller.
     * @param role     Ignored parameter kept for API compatibility.
     */
    public Manager(String id, String name, String password, Role role) {
        super(id, name, password, Role.MANAGER);

    }

    /**
     * Adds a new dish when basic validation passes and no duplicate name exists.
     *
     * @param dish Dish to add.
     * @return {@code true} if the dish was inserted, {@code false} otherwise.
     */
    public static boolean addDish(Dish dish) {
        if (dish == null)
            return false;
        if (dish.getName() == null || dish.getName().isBlank())
            return false;
        if (dish.getPrice() < 0)
            return false;
        if (dish.getCategory() == null)
            return false;

        List<Dish> existingDishes = dishRepo.findAll();
        for (Dish d : existingDishes) {
            if (d.getName().equalsIgnoreCase(dish.getName())) {
                return false;
            }
        }

        List<Dish> newDishes = new ArrayList<>();
        newDishes.add(dish);
        dishRepo.saveAll(newDishes);
        return true;
    }

    /**
     * Lists all dishes currently stored in persistence.
     *
     * @return All dishes.
     */
    public static List<Dish> listDishes() {
        return dishRepo.findAll();
    }

    /**
     * Deletes a dish by name (case-insensitive lookup).
     *
     * @param dishName Dish name to remove.
     * @return {@code true} if a dish was deleted, {@code false} otherwise.
     */
    public static boolean deleteDish(String dishName) {
        if (dishName == null || dishName.isBlank()) {
            return false;
        }

        List<Dish> existingDishes = dishRepo.findAll();
        String canonicalName = null;
        for (Dish d : existingDishes) {
            if (d.getName().equalsIgnoreCase(dishName.trim())) {
                canonicalName = d.getName();
                break;
            }
        }

        if (canonicalName == null) {
            return false;
        }

        return dishRepo.deleteByName(canonicalName);
    }
}
