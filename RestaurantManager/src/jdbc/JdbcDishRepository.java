package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.dish.Dish;
import model.enums.Allergen;
import model.enums.Category;


/**
 * Repository class for managing {@link Dish} entities in the database.
 * Provides CRUD operations and custom queries using JDBC.
 */
public class JdbcDishRepository {

    /**
     * Retrieves all dishes from the database.
     *
     * @return a list of all {@link Dish} objects found in the database.
     */
    public List<Dish> findAll() {
        List<Dish> dishes = new ArrayList<>();
        // Query to select necessary dish attributes
        String sql = "SELECT name, price, category, allergens FROM dishes";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            // Iterate over the result set and map each row to a Dish object
            while (rs.next()) {
                Dish dish = mapResultSetToDish(rs);
                if (dish != null)
                    dishes.add(dish);
            }

        } catch (SQLException e) {
            System.err.println("Error loading dishes: " + e.getMessage());
        }

        return dishes;
    }


    
    /**
     * Inserts only new dishes from the provided list.
     * Existing dishes remain untouched for granular database control.
     *
     * @param dishes the list of {@link Dish} objects to save.
     */
    public void saveAll(List<Dish> dishes) {
        String insertSql = "INSERT INTO dishes (name, price, category, allergens) VALUES (?, ?, ?, ?) "
                + "ON CONFLICT (name) DO NOTHING";

        try (Connection conn = JdbcConnectionFactory.connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                int inserted = 0;
                int skipped = 0;

                for (Dish d : dishes) {
                    insertStmt.setString(1, d.getName());
                    insertStmt.setDouble(2, d.getPrice());
                    insertStmt.setString(3, d.getCategory().name());
                    insertStmt.setString(4, serializeAllergens(d.getAllergens()));
                    int rows = insertStmt.executeUpdate();
                    if (rows == 1) {
                        inserted++;
                    } else {
                        skipped++;
                    }
                }

                conn.commit();
                System.out.println("   ✓ Inserted " + inserted + " new dishes");
                System.out.println("   ✓ Skipped " + skipped + " existing dishes");
                System.out.println("   ✓ Transaction committed");
            } catch (SQLException e) {
                try {
                    conn.rollback();
                    System.out.println("   ⚠️ Transaction rolled back");
                } catch (SQLException rollbackEx) {
                    System.err.println("   ❌ Rollback failed: " + rollbackEx.getMessage());
                }
                System.err.println("❌ Error saving dishes: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("❌ Error connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Finds dishes that contain a specific allergen.
     *
     * @param allergen the {@link Allergen} to search for.
     * @return a list of {@link Dish} objects containing the specified allergen.
     */
    public List<Dish> findByAllergen(Allergen allergen) {
        List<Dish> result = new ArrayList<>();
        // Query using LIKE to find the allergen in the serialized string
        String sql = "SELECT name, price, category, allergens FROM dishes WHERE allergens LIKE ?";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the wildcard parameter for the LIKE clause
            stmt.setString(1, "%" + allergen.name() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dish dish = mapResultSetToDish(rs);
                // Extra check to ensure exact match rather than partial string match
                if (dish != null && dish.getAllergens().contains(allergen)) {
                    result.add(dish);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error finding dishes by allergen: " + e.getMessage());
        }

        return result;
    }

    /**
     * Retrieves all dishes belonging to a specific category.
     *
     * @param category the {@link Category} to filter by.
     * @return a list of {@link Dish} objects in the given category.
     */
    public List<Dish> findByCategory(Category category) {
        List<Dish> result = new ArrayList<>();
        String sql = "SELECT name, price, category, allergens FROM dishes WHERE category = ?";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.name());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dish dish = mapResultSetToDish(rs);
                if (dish != null)
                    result.add(dish);
            }

        } catch (SQLException e) {
            System.err.println("Error finding dishes by category: " + e.getMessage());
        }

        return result;
    }

    /**
     * Retrieves dishes that are safe for a customer profile based on allergens.
     *
     * @param allergens Allergens to exclude from results.
     * @return Dishes that do not contain any excluded allergen.
     */
    public List<Dish> findSafeForAllergens(Set<Allergen> allergens) {
        if (allergens == null || allergens.isEmpty()) {
            return findAll();
        }

        List<Dish> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT name, price, category, allergens FROM dishes WHERE 1=1");
        for (int i = 0; i < allergens.size(); i++) {
            sql.append(" AND (allergens IS NULL OR allergens = '' OR allergens NOT LIKE ?)");
        }

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            for (Allergen allergen : allergens) {
                stmt.setString(index++, "%" + allergen.name() + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dish dish = mapResultSetToDish(rs);
                if (dish != null) {
                    result.add(dish);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error finding safe dishes by allergens: " + e.getMessage());
        }

        return result;
    }

    /**
     * Deletes a dish by its unique name.
     *
     * @param dishName Dish name to remove.
     * @return {@code true} if one row was deleted, {@code false} otherwise.
     */
    public boolean deleteByName(String dishName) {
        String deleteSql = "DELETE FROM dishes WHERE name = ?";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
            stmt.setString(1, dishName);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("No dish found with name: " + dishName);
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting dish: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Maps a current row in a ResultSet to a Dish object.
     *
     * @param rs the {@link ResultSet} positioned at the current row.
     * @return a {@link Dish} instance, or null if mapping fails.
     */
    private Dish mapResultSetToDish(ResultSet rs) {
        try {
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            Category category = Category.valueOf(rs.getString("category").toUpperCase());
            Set<Allergen> allergens = parseAllergens(rs.getString("allergens"));
            return new Dish(name, price, allergens, category);
        } catch (SQLException e) {
            System.err.println("Error mapping dish: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses a pipe-separated string of allergens into a set of {@link Allergen} enums.
     *
     * @param allergenString the delimited string of allergens (e.g., "DAIRY|NUTS").
     * @return a set of parsed {@link Allergen}s.
     */
    private Set<Allergen> parseAllergens(String allergenString) {
        Set<Allergen> allergens = new HashSet<>();
        if (allergenString == null || allergenString.trim().isEmpty())
            return allergens;
        for (String part : allergenString.split("\\|")) {
            try {
                allergens.add(Allergen.valueOf(part.trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.err.println("Unknown allergen: " + part.trim());
            }
        }
        return allergens;
    }

    /**
     * Serializes a set of {@link Allergen}s into a pipe-separated string for database storage.
     *
     * @param allergens the set of allergens to serialize.
     * @return a formatted string of allergens.
     */
    private String serializeAllergens(Set<Allergen> allergens) {
        if (allergens == null || allergens.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for (Allergen a : allergens) {
            if (sb.length() > 0)
                sb.append("|");
            sb.append(a.name());
        }
        return sb.toString();
    }
}