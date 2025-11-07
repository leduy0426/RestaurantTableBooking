/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.MenuDAO;
import dal.StaffDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;

/**
 *
 * @author fpt
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MenuDAO menuDAO = new MenuDAO();
        request.setAttribute("menuItems", menuDAO.getAllMenuItems());
        request.getRequestDispatcher("views/createOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int reservationId = Integer.parseInt(request.getParameter("reservation_id"));
            int staffId = Integer.parseInt(request.getParameter("staff_id"));
            double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
            String status = request.getParameter("status");
            String orderItemsJson = request.getParameter("order_items");

            // Create order
            Order order = new Order();
            order.setReservationId(reservationId);
            order.setStaffId(staffId);
            order.setTotalAmount(totalAmount);
            order.setStatus(status);

            StaffDAO staffDAO = new StaffDAO();
            int orderId = staffDAO.addOrderWithItems(order);

            if (orderId > 0 && orderItemsJson != null && !orderItemsJson.isEmpty()) {
                // Parse JSON string manually (simple JSON parser)
                // Format: [{"itemId":"1","itemName":"...","quantity":2,"price":100.0},...]
                try {
                    String json = orderItemsJson.trim();
                    if (json.startsWith("[") && json.endsWith("]")) {
                        json = json.substring(1, json.length() - 1); // Remove [ and ]
                        
                        // Handle empty array
                        if (json.trim().isEmpty()) {
                            return;
                        }
                        
                        // Split by },{ but handle single item case
                        String[] items;
                        if (json.contains("},{")) {
                            items = json.split("\\},\\{");
                            // Add back the braces
                            for (int i = 0; i < items.length; i++) {
                                if (i == 0) {
                                    items[i] = items[i] + "}";
                                } else if (i == items.length - 1) {
                                    items[i] = "{" + items[i];
                                } else {
                                    items[i] = "{" + items[i] + "}";
                                }
                            }
                        } else {
                            // Single item
                            items = new String[]{json};
                        }
                        
                        for (String itemStr : items) {
                            itemStr = itemStr.trim().replace("{", "").replace("}", "");
                            String[] pairs = itemStr.split(",");
                            
                            Integer menuItemId = null;
                            String itemName = null;
                            int quantity = 0;
                            double price = 0.0;
                            
                            for (String pair : pairs) {
                                String[] keyValue = pair.split(":", 2);
                                if (keyValue.length == 2) {
                                    String key = keyValue[0].trim().replace("\"", "").replace("'", "");
                                    String value = keyValue[1].trim().replace("\"", "").replace("'", "");
                                    
                                    if ("itemId".equals(key)) {
                                        try {
                                            menuItemId = Integer.parseInt(value);
                                        } catch (NumberFormatException e) {
                                            menuItemId = null;
                                        }
                                    } else if ("itemName".equals(key)) {
                                        itemName = value;
                                    } else if ("quantity".equals(key)) {
                                        quantity = Integer.parseInt(value);
                                    } else if ("price".equals(key)) {
                                        price = Double.parseDouble(value);
                                    }
                                }
                            }
                            
                            if (itemName != null && quantity > 0) {
                                staffDAO.addOrderItem(orderId, menuItemId, itemName, quantity, price);
                            }
                        }
                    }
                } catch (Exception parseEx) {
                    System.err.println("Error parsing order items JSON: " + parseEx.getMessage());
                    parseEx.printStackTrace();
                }
            }

            response.sendRedirect("OrderController");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CreateOrder?error=1");
        }
    }
}

