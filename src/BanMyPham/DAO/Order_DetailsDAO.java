/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.DTO.Order_Details;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Order_DetailsDAO {

    public ArrayList<Order_Details> getOrderDetailsList() {
        ArrayList<Order_Details> ordDtlList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Order_Details";
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order_Details orderDetail = new Order_Details();
                orderDetail.setOrderID(rs.getString(1));
                orderDetail.setProductID(rs.getString(2));
                orderDetail.setQuantity(rs.getInt(3));
                ordDtlList.add(orderDetail);
            }
        } catch (SQLException ex) {
        }
        return ordDtlList;
    }

    public ArrayList<Order_Details> getListOrderDetailsToOrderID(String orderID) {
        ArrayList<Order_Details> ordDtlList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Order_Details WHERE orderID=" + orderID;
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order_Details orderDetail = new Order_Details();
                orderDetail.setOrderID(rs.getString(1));
                orderDetail.setProductID(rs.getString(2));
                orderDetail.setQuantity(rs.getInt(3));
                ordDtlList.add(orderDetail);
            }
        } catch (SQLException ex) {
            return null;
        }
        return ordDtlList;
    }

    public ArrayList<Order_Details> getListOrderDetailsToProductID(String productID) {
        ArrayList<Order_Details> ordDtlList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Order_Details WHERE productID=" + productID;
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order_Details orderDetail = new Order_Details();
                orderDetail.setOrderID(rs.getString(1));
                orderDetail.setProductID(rs.getString(2));
                orderDetail.setQuantity(rs.getInt(3));
                ordDtlList.add(orderDetail);
            }
        } catch (SQLException ex) {
            return null;
        }
        return ordDtlList;
    }

    public boolean addOrderDetail(Order_Details orderDetail) {
        boolean result = false;
        try {
            String sql = "INSERT INTO Order_Details VALUES(?,?,?)";
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, orderDetail.getOrderID());
            prep.setString(2, orderDetail.getProductID());
            prep.setInt(3, orderDetail.getQuantity());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean deleteOrderDetail(String orderID, String productID) {
        boolean result = false;
        try {
            String sql = "DELETE FROM Order_Details WHERE orderID=" + orderID + " AND productID=" + productID;
            Statement stmt = ConnectionDatabase.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteOrderDetail(String orderID) {
        boolean result = false;
        try {
            String sql = "DELETE FROM cthoadon WHERE orderID=" + orderID;
            Statement stmt = ConnectionDatabase.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateOrderDetail(String orderID, String productID, Order_Details orderDetail) {
        boolean result = false;
        try {
            String sql = "UPDATE Order_Details SET orderID=?, productID=?, SoLuong=?"
                    + "WHERE orderID=? AND productID=?";
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, orderDetail.getOrderID());
            prep.setString(2, orderDetail.getProductID());
            prep.setInt(3, orderDetail.getQuantity());
            prep.setString(4, orderID);
            prep.setString(5, productID);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
