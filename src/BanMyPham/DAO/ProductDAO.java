/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.DTO.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO {

    public ArrayList<Product> getProductList() {
        try {
            String sql = "SELECT * FROM Product";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();

                product.setProductID(rs.getString(1));
                product.setProductCategoryID(rs.getString(2));
                product.setProductName(rs.getString(3));
                product.setDescription(rs.getString(4));
                product.setPrice(rs.getDouble(5));
                product.setQuantity(rs.getInt(6));
                product.setImage(rs.getString(7));
                product.setUnits(rs.getString(8));

                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
        }

        return null;
    }

    public Product getProduct(String productID) {
        try {
            String sql = "SELECT *FROM Product WHERE productID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, productID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Product product = new Product();

                product.setProductID(rs.getString(1));
                product.setProductCategoryID(rs.getString(2));
                product.setProductName(rs.getString(3));
                product.setDescription(rs.getString(4));
                product.setPrice(rs.getDouble(5));
                product.setQuantity(rs.getInt(6));
                product.setImage(rs.getString(7));
                product.setUnits(rs.getString(8));

                return product;
            }
        } catch (SQLException e) {
        }

        return null;
    }

    public ArrayList<Product> getProductSameType(String productCategoryID) {
        try {
            String sql = "SELECT * FROM Product WHERE productCategoryID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, productCategoryID);
            ResultSet rs = pre.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();

                product.setProductID(rs.getString(1));
                product.setProductCategoryID(rs.getString(2));
                product.setProductName(rs.getString(3));
                product.setDescription(rs.getString(4));
                product.setPrice(rs.getDouble(5));
                product.setQuantity(rs.getInt(6));
                product.setImage(rs.getString(7));
                product.setUnits(rs.getString(8));

                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
        }

        return null;
    }

    public String getProductImage(String productID) {
        try {
            String sql = "SELECT image FROM Product WHERE productID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, productID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString("image");
            }
        } catch (SQLException e) {
        }
        return "";
    }

    public void addQuantityOfProduct(String productID, int quantityAdded) {
        Product product = getProduct(productID);
        int quantity = product.getQuantity();
        product.setQuantity(quantity + quantityAdded);
        try {
            String sql = "UPDATE Product SET quantity=? WHERE productID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setInt(1, product.getQuantity());
            pre.setString(2, productID);
            pre.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public boolean addProduct(Product product) {
        try {
            String sql = "EXEC InsertProduct ?, ?, ?, ?, ?, ?,?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, product.getProductCategoryID());
            pre.setString(2, product.getProductName());
            pre.setString(3, product.getDescription());
            pre.setDouble(4, product.getPrice());
            pre.setInt(5, product.getQuantity());
            pre.setString(6, product.getImage());
            pre.setString(7, product.getUnits());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean inputProductFromExcel(Product product) {
        try {
            String sql = "DELETE * FROM Product; "
                    + "INSERT INTO Product(productID,productCategoryID,productName,description,price,quantity,image,units) "
                    + "VALUES (?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, product.getProductID());
            pre.setString(2, product.getProductCategoryID());
            pre.setString(3, product.getProductName());
            pre.setString(4, product.getDescription());
            pre.setDouble(5, product.getPrice());
            pre.setInt(6, product.getQuantity());
            pre.setString(7, product.getImage());
            pre.setString(8, product.getUnits());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean deleteProduct(String productID) {
        try {
            String sql = "DELETE FROM Product WHERE productID=?";
            PreparedStatement ps = ConnectionDatabase.conn.prepareStatement(sql);
            ps.setString(1, productID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editProduct(Product product) {
        try {
            String sql = "UPDATE Product SET "
                    + "productName=?, "
                    + "productCategoryID=?, description=?, price=?, quantity=?, image=?, units=? "
                    + "WHERE productID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setString(2, product.getProductCategoryID());
            pre.setString(3, product.getDescription());
            pre.setDouble(4, product.getPrice());
            pre.setInt(5, product.getQuantity());
            pre.setString(6, product.getImage());
            pre.setString(7, product.getUnits());

            pre.setString(8, product.getProductID());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
