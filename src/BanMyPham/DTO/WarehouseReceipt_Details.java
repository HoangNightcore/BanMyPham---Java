/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DTO;

/**
 *
 * @author acer
 */
public class WarehouseReceipt_Details {
    private String warehouseReceiptID;
    private String productID;
    private int quantity;
    private double price;
    private double totalCost;

    public WarehouseReceipt_Details() {
    }

    public WarehouseReceipt_Details(String warehouseReceiptID, String productID, int quantity, double price, double totalCost) {
        this.warehouseReceiptID = warehouseReceiptID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.totalCost = totalCost;
    }

    public String getWarehouseReceiptID() {
        return warehouseReceiptID;
    }

    public void setWarehouseReceiptID(String warehouseReceiptID) {
        this.warehouseReceiptID = warehouseReceiptID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
}
