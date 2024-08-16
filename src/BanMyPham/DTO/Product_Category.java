/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DTO;

/**
 *
 * @author acer
 */
public class Product_Category {
    private String productCategoryID;
    private String productCategoryName;

    public Product_Category() {
    }

    public Product_Category(String productCategoryID, String productCategoryName) {
        this.productCategoryID = productCategoryID;
        this.productCategoryName = productCategoryName;
    }

    public String getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
    
    
}
