/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.DTO.Product_Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class Product_CategoryDAO {

    public ArrayList<Product_Category> getProductCategoryList() {
        try {
            String sql = "SELECT * FROM ProductCategory";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<Product_Category> productCategoryList = new ArrayList<>();
            while (rs.next()) {
                Product_Category productCategory = new Product_Category();
                productCategory.setProductCategoryID(rs.getString(1));
                productCategory.setProductCategoryName(rs.getString(2));
                productCategoryList.add(productCategory);
            }
            return productCategoryList;
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean addProductCategory(Product_Category productCategory) {
        try {
            String sql = "insert into ProductCategory(productCategoryID, productCategoryName) "
                    + "values ("
                    + "'" + productCategory.getProductCategoryID()+ "',"
                    + "'" + productCategory.getProductCategoryName()+ "')";
            Statement st = ConnectionDatabase.conn.createStatement();
            int x = st.executeUpdate(sql);
            return x > 0 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(Product_CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteCategory(String categoryID) {
        try {
            String sql = "DELETE FROM ProductCategory WHERE productCategoryID=" + categoryID;
            Statement st = ConnectionDatabase.conn.createStatement();
            int x = st.executeUpdate(sql);
            return x > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean editCategory(String categoryID, String categoryName) {
        try {
            String sql = "UPDATE ProductCategory SET productCategoryName='" + categoryName + "' WHERE productCategoryID=" + categoryID;
            Statement st = ConnectionDatabase.conn.createStatement();
            int x = st.executeUpdate(sql);
            return x > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }
}
