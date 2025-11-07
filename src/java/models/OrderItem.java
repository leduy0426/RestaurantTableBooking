/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author fpt
 */
public class OrderItem {
    private int id;
    private int orderId;
    private Integer menuItemId; // ID của món trong menu (nullable vì món có thể bị xóa)
    private String itemName;
    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(int id, int orderId, String itemName, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(int id, int orderId, Integer menuItemId, String itemName, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }
}
