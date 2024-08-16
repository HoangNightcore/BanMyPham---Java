/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.DTO.Product_Sale;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author acer
 */
public class Product_SaleDAO {

    public ArrayList<Product_Sale> getSaleList() {
        try {
            String sql = "SELECT * FROM Product_Sale";
            Statement st = ConnectionDatabase.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Product_Sale> saleList = new ArrayList<>();
            while (rs.next()) {
                Product_Sale sale = new Product_Sale();
                sale.setSaleID(rs.getString(1));
                sale.setSaleName(rs.getString(2));
                sale.setDiscountPercentage(rs.getDouble(3));
                sale.setCondition(rs.getInt(4));
                sale.setStartDate(rs.getDate(5));
                sale.setEndDate(rs.getDate(6));
                sale.setIsActive(rs.getBoolean(7));
                saleList.add(sale);
            }
            return saleList;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean addSale(Product_Sale sale) {
        try {
            String sql = "EXEC InsertProductSale ?, ?, ?, ?, ?, ?"; // Thay đổi câu lệnh SQL để chỉ đưa vào các tham số cần thiết cho stored procedure
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, sale.getSaleName()); // Truyền tham số cho saleName
            pre.setDouble(2, sale.getDiscountPercentage()); // Truyền tham số cho discountPercentage
            pre.setInt(3, sale.getCondition()); // Truyền tham số cho condition
            
            pre.setTimestamp(4, new java.sql.Timestamp(sale.getStartDate().getTime()));
            pre.setTimestamp(5, new java.sql.Timestamp(sale.getEndDate().getTime()));
            
            pre.setBoolean(6, sale.isIsActive());

            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace(); // Thông báo lỗi nếu có lỗi xảy ra
        }
        return false;
    }

    public boolean editSale(Product_Sale sale) {
        try {
            String sql = "UPDATE Product_Sale SET saleName=?, discountPercentage=?, condition=?, startDate=?, endDate=?, isActive=? WHERE saleID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, sale.getSaleName());
            pre.setDouble(2, sale.getDiscountPercentage());
            pre.setInt(3, sale.getCondition());

            pre.setTimestamp(4, new java.sql.Timestamp(sale.getStartDate().getTime()));
            pre.setTimestamp(5, new java.sql.Timestamp(sale.getEndDate().getTime()));
            
            pre.setBoolean(6, sale.isIsActive());

            pre.setString(7, sale.getSaleID());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
