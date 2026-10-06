package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Factory utility for creating PostgreSQL JDBC connections.
 */
public final class JdbcConnectionFactory {

    private static final String DB_URL = "jdbc:postgresql://32.193.176.159:5432/fpdatabase";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "123456";

    private JdbcConnectionFactory() {
        // Utility class
    }

    /**
     * Opens a new JDBC connection using the configured database credentials.
     *
     * @return Open JDBC connection.
     * @throws SQLException If connection establishment fails.
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
