/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.DTO.Orders;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class OrdersDAO {

    public ArrayList<Orders> getListOrders() {
        ArrayList<Orders> listOrders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Orders";
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderID(rs.getString(1));
                order.setUserID(rs.getString(2));
                order.setCustomerID(rs.getString(3));
                order.setOrderStatusID(rs.getString(4));
                order.setOrderDate(rs.getDate(5));
                order.setTotalCost(rs.getDouble(6));
                listOrders.add(order);
            }
        } catch (SQLException ex) {
            return null;
        }
        return listOrders;
    }

    public boolean addOrder(Orders order) {
        boolean result = false;
        try {
            String sql1 = "UPDATE Customer SET totalSpending=totalSpending+" + order.getTotalCost() + " WHERE customerID='" + order.getCustomerID()+"'";
            Statement st = ConnectionDatabase.conn.createStatement();
            st.executeUpdate(sql1);
            String sql = "EXEC InsertOrder ?, ?, ?, ?,?";
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, order.getUserID());
            prep.setString(2, order.getCustomerID());
            prep.setString(3, order.getOrderStatusID());
            prep.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            System.out.println(order.getOrderStatusID());
            prep.setDouble(5, order.getTotalCost());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public String getOrderIDLatest() {
        try {
            String sql = "SELECT MAX(orderID) FROM Orders";
            Statement st = ConnectionDatabase.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<Orders> getListOrders(Date dateMin, Date dateMax) {
        try {
            String sql = "SELECT * FROM Orders WHERE orderDate BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setDate(1, dateMin);
            pre.setDate(2, dateMax);
            ResultSet rs = pre.executeQuery();

            ArrayList<Orders> listOrders = new ArrayList<>();

            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderID(rs.getString(1));
                order.setUserID(rs.getString(2));
                order.setCustomerID(rs.getString(3));
                order.setOrderStatusID(rs.getString(4));
                order.setOrderDate(rs.getDate(5));
                order.setTotalCost(rs.getDouble(6));
                listOrders.add(order);
            }
            return listOrders;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
