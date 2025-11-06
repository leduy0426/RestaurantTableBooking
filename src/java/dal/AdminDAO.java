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
            ResultSet rs = ps.executeQuery();
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
            e.printStackTrace();
        }
        return null; // không tìm thấy tài khoản
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
}
