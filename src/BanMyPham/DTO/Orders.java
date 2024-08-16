/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DTO;

import java.util.Date;


public class Orders {
    private String orderID;
    private String userID;
    private String customerID;
    private String orderStatusID;
    private Date orderDate;
    private double totalCost;

    public Orders() {
    }

    public Orders(String orderID, String userID, String customerID, String orderStatusID, Date orderDate, double totalCost) {
        this.orderID = orderID;
        this.userID = userID;
        this.customerID = customerID;
        this.orderStatusID = orderStatusID;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(String orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    
}
