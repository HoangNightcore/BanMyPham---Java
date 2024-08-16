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
public class Product_Sale {
    private String saleID;
    private String saleName;
    private double discountPercentage;
    private int condition;
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    public Product_Sale() {
    }

    public Product_Sale(String saleID, String saleName, double discountPercentage, int condition, Date startDate, Date endDate, boolean isActive) {
        this.saleID = saleID;
        this.saleName = saleName;
        this.discountPercentage = discountPercentage;
        this.condition = condition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }


    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleName() {
        return saleName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isIsActive() {
        return isActive;
    }


    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
