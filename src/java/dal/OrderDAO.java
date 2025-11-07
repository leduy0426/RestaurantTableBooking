/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import models.Order;
/**
 *
 * @author fpt
 */
public class OrderDAO extends DBContext{
      PreparedStatement stm;
    ResultSet rs;
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
    
      public void updateOrderTotal(int orderId, double totalAmount) {
        try {
            String sql = "UPDATE orders SET total_amount = ? WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setDouble(1, totalAmount);
            stm.setInt(2, orderId);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
