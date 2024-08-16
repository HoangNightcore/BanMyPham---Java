/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DTO;


import java.util.Date;

/**
 *
 * @author acer
 */
public class WarehouseReceipt {
    private String warehouseReceiptID;
    private String supplierID;
    private String userID;
    private Date dateAdded;
    private double totalCost;

    public WarehouseReceipt() {
    }

    public WarehouseReceipt(String warehouseReceiptID, String supplierID, String userID, Date dateAdded, double totalCost) {
        this.warehouseReceiptID = warehouseReceiptID;
        this.supplierID = supplierID;
        this.userID = userID;
        this.dateAdded = dateAdded;
        this.totalCost = totalCost;
    }

    public String getWarehouseReceiptID() {
        return warehouseReceiptID;
    }

    public void setWarehouseReceiptID(String warehouseReceiptID) {
        this.warehouseReceiptID = warehouseReceiptID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
}
