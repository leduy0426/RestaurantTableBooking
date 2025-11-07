package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.Admin;
import models.Staff;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

public class AdminDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public Admin checkLogin(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setFullName(rs.getString("full_name"));
                a.setEmail(rs.getString("email"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Staff> getStaff() {
        List<Staff> staffs = new ArrayList<Staff>();
        try {
            String strSQL = "select * from staff";
            stm = connection.prepareCall(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                Date dob = rs.getDate("birthdate");
                String position = rs.getString("position");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String username = rs.getString("username");
                String password = rs.getString("password");
                staffs.add(new Staff(id, fullName, dob, position, phone, email, address, username, password));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return staffs;
    }

    public List<Staff> getStaffByName(String name) {
        List<Staff> staffs = new ArrayList<Staff>();
        try {
            String strSQL = "select * from staff where full_name like ?";
            stm = connection.prepareCall(strSQL);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                Date dob = rs.getDate("birthdate");
                String position = rs.getString("position");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String username = rs.getString("username");
                String password = rs.getString("password");
                staffs.add(new Staff(id, fullName, dob, position, phone, email, address, username, password));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return staffs;
    }

    public Staff getStaffByID(String ID) {
        Staff staff = new Staff();
        try {
            String strSQL = "select * from staff where id = ?";
            stm = connection.prepareCall(strSQL);
            stm.setString(1, ID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                Date dob = rs.getDate("birthdate");
                String position = rs.getString("position");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String username = rs.getString("username");
                String password = rs.getString("password");
                staff = new Staff(id, fullName, dob, position, phone, email, address, username, password);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return staff;
    }

    public boolean addStaff(Staff staff) {
        String sql = "INSERT INTO staff (full_name, birthdate, position, phone, email, address, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, staff.getFull_name());

            if (staff.getBirthdate() != null) {
                stm.setDate(2, new java.sql.Date(staff.getBirthdate().getTime()));
            } else {
                stm.setNull(2, java.sql.Types.DATE);
            }

            stm.setString(3, staff.getPosition());
            stm.setString(4, staff.getPhone());
            stm.setString(5, staff.getEmail());
            stm.setString(6, staff.getAddress());
            stm.setString(7, staff.getUsername());
            stm.setString(8, staff.getPassword());

            return stm.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi addStaff: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStaff(int id) {
        String sql = "DELETE FROM staff WHERE id = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi deleteStaff: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET full_name=?, birthdate=?, position=?, phone=?, email=?, address=?, password=? WHERE id=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, staff.getFull_name());
            if (staff.getBirthdate() != null) {
                stm.setDate(2, new java.sql.Date(staff.getBirthdate().getTime()));
            } else {
                stm.setNull(2, java.sql.Types.DATE);
            }
            stm.setString(3, staff.getPosition());
            stm.setString(4, staff.getPhone());
            stm.setString(5, staff.getEmail());
            stm.setString(6, staff.getAddress());
            stm.setString(7, staff.getPassword());
            stm.setInt(8, staff.getId());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi updateStaff: " + e.getMessage());
            return false;
        }
    }

}
