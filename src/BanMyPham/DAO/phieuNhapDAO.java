/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.DTO.WarehouseReceipt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class phieuNhapDAO {

    public ArrayList<WarehouseReceipt> getDSphieuNhap() {
        ArrayList<WarehouseReceipt> dspn = new ArrayList<>();
        try {
            String sql = "select * from WarehouseReceipt";
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                WarehouseReceipt pn = new WarehouseReceipt();
                pn.setWarehouseReceiptID(rs.getString("warehouseReceiptID"));
                pn.setUserID(rs.getString("userID"));
                pn.setSupplierID(rs.getString("supplierID"));
                pn.setDateAdded(rs.getDate("dateAdded"));
                pn.setTotalCost(rs.getDouble("totalCost"));
                dspn.add(pn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dspn;
    }

    public boolean themPhieuNhap(WarehouseReceipt pn) {
        boolean result = false;
        try {
            String sql = "EXEC InsertWarehouseReceipt @supplierID = ?, @userID =?, @dateAdded = ?, @totalCost = ?;";
            PreparedStatement preg = ConnectionDatabase.conn.prepareStatement(sql);
            preg.setString(1, pn.getSupplierID());
            preg.setString(2, pn.getUserID());
            preg.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            preg.setDouble(4, pn.getTotalCost());

            result = preg.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String getLastID() {
        try {
            String sql = "SELECT MAX(warehouseReceiptID) FROM WarehouseReceipt";
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

}
