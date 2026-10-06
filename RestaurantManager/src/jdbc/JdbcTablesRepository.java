package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.table.Table;

/**
 * Repository class for loading and persisting {@link Table} entities using JDBC.
 */
public class JdbcTablesRepository {

    /**
     * Loads all tables from persistence.
     *
     * @return List of tables currently stored in the database.
     */
    public List<Table> loadAll() {
        List<Table> tables = new ArrayList<>();
        String sql = "SELECT id, capacity FROM tables";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            int maxId = 0;
            while (rs.next()) {
                Table table = new Table(rs.getInt("capacity"));
                table.setId(rs.getString("id"));
                tables.add(table);

                int num = Integer.parseInt(rs.getString("id").replace("T", ""));
                if (num > maxId)
                    maxId = num;
            }
            Table.setNextId(maxId + 1);

        } catch (SQLException e) {
            System.err.println("Error loading tables: " + e.getMessage());
        }

        return tables;
    }

    /**
     * Persists new tables while skipping IDs that already exist.
     *
     * @param tables Tables to persist.
     */
    public void saveAll(List<Table> tables) {
        String insertSql = "INSERT INTO tables (id, capacity) VALUES (?, ?) ON CONFLICT (id) DO NOTHING";

        try (Connection conn = JdbcConnectionFactory.connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                int inserted = 0;
                int skipped = 0;

                for (Table t : tables) {
                    insertStmt.setString(1, t.getId());
                    insertStmt.setInt(2, t.getCapacity());
                    int rows = insertStmt.executeUpdate();
                    if (rows == 1) {
                        inserted++;
                    } else {
                        skipped++;
                    }
                }

                conn.commit();
                System.out.println("   ✓ Inserted " + inserted + " new tables");
                System.out.println("   ✓ Skipped " + skipped + " existing tables");
                System.out.println("   ✓ Transaction committed");
            } catch (SQLException e) {
                try {
                    conn.rollback();
                    System.out.println("   ⚠️ Transaction rolled back");
                } catch (SQLException rollbackEx) {
                    System.err.println("   ✗ Rollback failed: " + rollbackEx.getMessage());
                }
                System.err.println("✗ Error saving tables: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("✗ Error connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }
}