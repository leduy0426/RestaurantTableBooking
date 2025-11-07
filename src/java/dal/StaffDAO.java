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
import models.Order;
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
      
       public Reservation getCustomerById(int id){

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
      
    public Reservation createCustomer(Reservation customer) {
        Reservation found = getCustomerById(customer.getId());
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
  public List<Reservation> getCustomerByName(String name) {
    List<Reservation> customerList = new ArrayList<>();
    String sql = "SELECT * FROM reservation WHERE customer_name LIKE ?";

    try {
        stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + name + "%");  // tìm gần đúng
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
        e.printStackTrace();
    }

    return customerList;
}

public void updateReservation(int id, String name, String phone, int tableNumber,
                              String reservationTime, int numPeople, String note) {
    try {
        String sql = "UPDATE reservation SET customer_name=?, phone=?, table_number=?, reservation_time=?, num_people=?, note=? WHERE id=?";
        stm = connection.prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, phone);
        stm.setInt(3, tableNumber);
        stm.setString(4, reservationTime);
        stm.setInt(5, numPeople);
        stm.setString(6, note);
        stm.setInt(7, id);
        stm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void deleteReservation(int id) {
    try {
        String sql = "DELETE FROM reservation WHERE id = ?";
        stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        stm.executeUpdate();
        System.out.println("Deleted reservation ID: " + id);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM orders";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setReservationId(rs.getInt("reservation_id"));
                order.setStaffId(rs.getInt("staff_id"));
                order.setOrderTime(rs.getString("order_time"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addOrder(Order o) {
        try {
            String sql = "INSERT INTO orders (reservation_id, staff_id, total_amount, status) VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, o.getReservationId());
            stm.setInt(2, o.getStaffId());
            stm.setDouble(3, o.getTotalAmount());
            stm.setString(4, o.getStatus());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int addOrderWithItems(Order o) {
        int orderId = -1;
        try {
            // Insert order and get the generated ID
            String sql = "INSERT INTO orders (reservation_id, staff_id, total_amount, status, order_time) VALUES (?, ?, ?, ?, GETDATE())";
            stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, o.getReservationId());
            stm.setInt(2, o.getStaffId());
            stm.setDouble(3, o.getTotalAmount());
            stm.setString(4, o.getStatus());
            stm.executeUpdate();
            
            // Get the generated order ID
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public void addOrderItem(int orderId, String itemName, int quantity, double price) {
        addOrderItem(orderId, null, itemName, quantity, price);
    }

    public void addOrderItem(int orderId, Integer menuItemId, String itemName, int quantity, double price) {
        try {
            String sql = "INSERT INTO order_items (order_id, menu_item_id, item_name, quantity, price) VALUES (?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            if (menuItemId != null) {
                stm.setInt(2, menuItemId);
            } else {
                stm.setNull(2, java.sql.Types.INTEGER);
            }
            stm.setString(3, itemName);
            stm.setInt(4, quantity);
            stm.setDouble(5, price);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<models.OrderItem> getOrderItemsByOrderId(int orderId) {
        List<models.OrderItem> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM order_items WHERE order_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            rs = stm.executeQuery();

            while (rs.next()) {
                models.OrderItem item = new models.OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                int menuItemIdValue = rs.getInt("menu_item_id");
                if (!rs.wasNull()) {
                    item.setMenuItemId(menuItemIdValue);
                }
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

