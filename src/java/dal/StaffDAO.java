/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Reservation;
import models.Staff;
/**
 *
 * @author fpt
 */
public class StaffDAO extends DBContext{
     PreparedStatement stm;
    ResultSet rs;
    
      public Staff checkLogin(String username, String password) {
        String sql = "SELECT * FROM staff WHERE username = ? AND password = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
             rs = stm.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setFull_name(rs.getString("full_name"));
                staff.setBirthdate(rs.getDate("birthdate"));
                staff.setPosition(rs.getString("position"));
                staff.setPhone(rs.getString("phone"));
                staff.setEmail(rs.getString("email"));
                staff.setAddress(rs.getString("address"));
                
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                return staff;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
      
       public Reservation GetCustomerById(int id){

        Reservation customer = null;
        try {
            String strSQL = "select * from reservation where id = ?"; 
            stm = connection.prepareStatement(strSQL);
            stm.setInt(1, id); 
                                         
            rs = stm.executeQuery();     
                                          
            while (rs.next()) {                
                int customerid = rs.getInt("id");
                String name = rs.getString("customer_name");
                String phone = rs.getString("phone");
                int table_number = rs.getInt("table_number");
                String reservationTime = rs.getString("reservation_time");
                int numPeople = rs.getInt("num_people");
                String note = rs.getString("note");
             
                customer = new Reservation(id, name, phone, table_number, reservationTime, numPeople, note);
            }
        } catch (Exception ex) {
            System.out.println("GetAccountById:" + ex.getMessage());
        }
   return customer;
    }
      
    public Reservation CreateCustomer(Reservation customer) {
        Reservation found = GetCustomerById(customer.getId());
        if (found != null) {
            System.out.println("Customer had already exist!");
            return null;
        }

        try {
            String strSQL = "INSERT INTO reservation(customer_name, phone, table_number, reservation_time, num_people, note) VALUES (?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(strSQL);
        
            stm.setString(1, customer.getCustomerName());
            stm.setString(2, customer.getPhone());
            stm.setInt(3, customer.getTableNumber());
            
                String timeStr = customer.getReservationTime(); 
        if (timeStr != null && !timeStr.trim().isEmpty()) {
            timeStr = timeStr.replace("T", " ");     
            if (timeStr.split(":").length == 2) {
                timeStr += ":00";                    
            }
            stm.setString(4, timeStr);
        } else {
            stm.setNull(4, java.sql.Types.TIMESTAMP);
        }

        stm.setInt(5, customer.getNumPeople());
         stm.setString(6, customer.getNote());
            stm.execute();
           
        } catch (Exception ex) {
            System.out.println("CreateAccount: " + ex.getMessage());
        }
        return customer;
    }
    
   public List<Reservation> getAllCutomer() {
       List<Reservation> customerList = new ArrayList<>();
       
       try {
           String SQL = "select * from reservation";
           stm = connection.prepareStatement(SQL);
           rs = stm.executeQuery();
           
           while (rs.next()) {               
               
         Reservation customer = new Reservation();
         customer.setId(rs.getInt("id"));
         customer.setCustomerName(rs.getString("customer_name"));
         customer.setPhone(rs.getString("phone"));
         customer.setTableNumber(rs.getInt("table_number"));
         customer.setReservationTime(rs.getString("reservation_time"));
         
            customer.setNumPeople(rs.getInt("num_people"));
            customer.setNote(rs.getString("note"));
            
        
            customerList.add(customer);
           }
       } catch (Exception e) {
       }
    
  return customerList;
}
   
   
}