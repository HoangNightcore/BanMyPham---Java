/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DTO;

/**
 *
 * @author acer
 */
public class Order_Status {
    private String orderStatusID;
    private String orderStatusName;

    public Order_Status() {
    }

    public Order_Status(String orderStatusID, String orderStatusName) {
        this.orderStatusID = orderStatusID;
        this.orderStatusName = orderStatusName;
    }

    public String getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(String orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }
    
    
}
