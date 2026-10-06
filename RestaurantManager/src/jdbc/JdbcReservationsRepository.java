package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.enums.ReservationStatus;
import model.table.Reservation;
import model.table.Shift;
import model.table.Table;
import model.user.Customer;

/**
 * Repository class for loading and persisting {@link Reservation} entities using JDBC.
 */
public class JdbcReservationsRepository {

    /**
     * Loads all reservations from persistence and maps them to domain objects.
     *
     * @param tables In-memory table catalog used to resolve table references.
     * @return List of reservations currently stored in the database.
     */
    public List<Reservation> loadAll(List<Table> tables) {
        List<Reservation> reservations = new ArrayList<>();

        // Expire any PENDING reservations whose shift window has already passed
        try (Connection expireConn = JdbcConnectionFactory.connect();
                PreparedStatement expireStmt = expireConn.prepareStatement("SELECT expire_stale_reservations()")) {
            expireStmt.execute();
        } catch (SQLException e) {
            System.err.println("Warning: could not expire stale reservations: " + e.getMessage());
        }

        String sql = "SELECT id, customer_id, customer_name, table_id, num_seats, shift, date, status FROM reservations";

        try (Connection conn = JdbcConnectionFactory.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String tableId = rs.getString("table_id");
                Table table = null;
                for (Table t : tables) {
                    if (t.getId().equals(tableId)) {
                        table = t;
                        break;
                    }
                }
                if (table == null)
                    continue;

                Customer customer = new Customer(
                        rs.getString("customer_id"),
                        rs.getString("customer_name"),
                        "1234",
                        Collections.emptySet());

                Shift shift = getShiftById(rs.getString("shift"));
                LocalDate date = rs.getDate("date").toLocalDate();
                ReservationStatus status = ReservationStatus.valueOf(rs.getString("status"));

                Reservation reservation = new Reservation(customer, table, rs.getInt("num_seats"), shift, date);
                reservation.setId(rs.getString("id"));
                reservation.setStatusFromPersistence(status);

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            System.err.println("Error loading reservations: " + e.getMessage());
        }

        return reservations;
    }

    /**
     * Persists new reservations, skipping IDs that already exist in persistence.
     *
     * @param reservations Reservations to persist.
     */
    public void saveAll(List<Reservation> reservations) {
        String insertSql = "INSERT INTO reservations (id, customer_id, customer_name, table_id, num_seats, shift, date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";

        try (Connection conn = JdbcConnectionFactory.connect()) {
            conn.setAutoCommit(false);
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                int inserted = 0;
                int skipped = 0;

                for (Reservation r : reservations) {
                    insertStmt.setString(1, r.getId());
                    insertStmt.setString(2, r.getCustomer().getId());
                    insertStmt.setString(3, r.getCustomer().getName());
                    insertStmt.setString(4, r.getTable().getId());
                    insertStmt.setInt(5, r.getNumSeats());
                    insertStmt.setString(6, r.getShift().getId());
                    insertStmt.setDate(7, java.sql.Date.valueOf(r.getDate()));
                    insertStmt.setString(8, r.getStatus().name());
                    int rows = insertStmt.executeUpdate();
                    if (rows == 1) {
                        inserted++;
                    } else {
                        skipped++;
                    }
                }

                conn.commit();
                System.out.println("   ✓ Inserted " + inserted + " new reservations");
                System.out.println("   ✓ Skipped " + skipped + " existing reservations");
                System.out.println("   ✓ Transaction committed");
            } catch (SQLException e) {
                try {
                    conn.rollback();
                    System.out.println("   ⚠️ Transaction rolled back");
                } catch (SQLException rollbackEx) {
                    System.err.println("   ❌ Rollback failed: " + rollbackEx.getMessage());
                }
                System.err.println("❌ Error saving reservations: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("❌ Error connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Updates a reservation status by identifier.
     *
     * @param reservationId Reservation identifier.
     * @param status        New status to store.
     * @return {@code true} if at least one row was updated.
     */
    public boolean updateReservationStatus(String reservationId, ReservationStatus status) {
        String updateSql = "UPDATE reservations SET status = ? WHERE id = ?";

        try (Connection conn = JdbcConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {
            stmt.setString(1, status.name());
            stmt.setString(2, reservationId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.err.println("No reservation updated for id: " + reservationId);
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating reservation status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    



    /**
     * Maps a persisted shift identifier to the predefined shift instance.
     *
     * @param id Persisted shift identifier.
     * @return Matching shift instance.
     */
    private Shift getShiftById(String id) {
        return switch (id) {
            case "LUNCH_1" -> Shift.LUNCH1;
            case "LUNCH_2" -> Shift.LUNCH2;
            case "DINNER_1" -> Shift.DINNER1;
            case "DINNER_2" -> Shift.DINNER2;
            default -> throw new IllegalArgumentException("Unknown shift: " + id);
        };
    }
}