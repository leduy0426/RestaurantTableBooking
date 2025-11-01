//package dal;
//
//import java.sql.PreparedStatement;
//import model.Reservation;
//
//public class ReservationDAO extends DBContext {
//
//    public ReservationDAO() {
//        super(); // ✅ đảm bảo mở connection
//    }
//
//    public boolean addReservation(Reservation r) {
//        String sql = "INSERT INTO reservation (customer_name, phone, table_number, reservation_time, num_people, note) VALUES (?, ?, ?, ?, ?, ?)";
////String sql = "INSERT INTO reservation (customer_name, phone, table_number, reservation_time, num_people, note) VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, r.getCustomerName());
//            ps.setString(2, r.getPhone());
//            ps.setInt(3, r.getTableNumber());
//            
//            ps.setString(4, r.getReservationTime());
//            ps.setInt(5, r.getNumPeople());
//            ps.setString(6, r.getNote());
//
//            int rows = ps.executeUpdate();
//            if (rows > 0) {
//                System.out.println("✅ Thêm reservation thành công");
//                return true;
//            } else {
//                System.out.println("⚠️ Không có dòng nào được thêm");
//            }
//
//        } catch (Exception e) {
//            System.out.println("❌ addReservation lỗi: " + e.getMessage());
//        }
//
//        return false;
//    }
//
//}
package dal;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import model.Reservation;

public class ReservationDAO extends DBContext {

    public ReservationDAO() {
        super();
    }

    public boolean addReservation(Reservation r) {
        String sql = "INSERT INTO reservation (customer_name, phone, table_number, reservation_time, num_people, note) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, r.getCustomerName());
            ps.setString(2, r.getPhone());
            ps.setInt(3, r.getTableNumber());

            // ✅ Convert string thành Timestamp
            //Timestamp ts = Timestamp.valueOf(r.getReservationTime());
            Timestamp ts = Timestamp.valueOf(r.getReservationTime().replace("T", " ") + ":00");

            ps.setTimestamp(4, ts);

            ps.setInt(5, r.getNumPeople());
            ps.setString(6, r.getNote());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("❌ addReservation lỗi: " + e.getMessage());
            return false;
        }
    }
}
