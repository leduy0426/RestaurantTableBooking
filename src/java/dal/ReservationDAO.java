
package dal;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import models.Reservation;

public class ReservationDAO extends DBContext {

    public ReservationDAO() {
        super();
    }

    public boolean addReservation(Reservation r) {
        String sql = "INSERT INTO reservation (customer_name, phone, table_number, reservation_time, num_people, note) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Check connection
            if (connection == null) {
                System.out.println("❌ Database connection is null");
                return false;
            }

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, r.getCustomerName());
                ps.setString(2, r.getPhone());
                ps.setInt(3, r.getTableNumber());
                
                // Convert datetime-local format to Timestamp
                String timeStr = r.getReservationTime();
                if (timeStr == null || timeStr.trim().isEmpty()) {
                    System.out.println("❌ Reservation time is null or empty");
                    return false;
                }
                
                // Format: "2025-01-15T14:30" -> "2025-01-15 14:30:00"
                timeStr = timeStr.replace("T", " ");
                if (timeStr.split(":").length == 2) {
                    timeStr += ":00";
                }
                
                try {
                    Timestamp ts = Timestamp.valueOf(timeStr);
                    ps.setTimestamp(4, ts);
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Invalid timestamp format: " + timeStr);
                    System.out.println("❌ Error: " + e.getMessage());
                    return false;
                }
                
                ps.setInt(5, r.getNumPeople());
                
                // Handle null note
                String note = r.getNote();
                if (note == null || note.trim().isEmpty()) {
                    ps.setNull(6, java.sql.Types.NVARCHAR);
                } else {
                    ps.setString(6, note);
                }
                
                int rows = ps.executeUpdate();
                System.out.println("✅ Inserted " + rows + " row(s)");
                return rows > 0;

            }
        } catch (java.sql.SQLException e) {
            System.out.println("❌ SQL Exception: " + e.getMessage());
            System.out.println("❌ SQL State: " + e.getSQLState());
            System.out.println("❌ Error Code: " + e.getErrorCode());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("❌ addReservation lỗi: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
