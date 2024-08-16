/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.DTO.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class NhaCungCapDAO {

    public ArrayList<Supplier> getListNCC() {
        ArrayList<Supplier> dsNCC = new ArrayList<>();
        String sql = "SELECT * FROM Supplier";

        try (PreparedStatement pstmt = ConnectionDatabase.conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Supplier ncc = new Supplier();
                ncc.setSupplierID(rs.getString("supplierID"));
                ncc.setSupplierName(rs.getString("supplierName"));
                ncc.setAddress(rs.getString("address"));
                ncc.setPhoneNumber(rs.getString("phoneNumber"));
                dsNCC.add(ncc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return dsNCC;
    }

    public boolean AddNCC(Supplier ncc) {
        boolean result = false;
        try {
            String sql = "EXEC InsertSupplier @supplierName = ?, @address = ?, @phoneNumber = ?;";
            PreparedStatement preg = ConnectionDatabase.conn.prepareStatement(sql);
            preg.setString(1, ncc.getSupplierName());
            preg.setString(2, ncc.getAddress());
            preg.setString(3, ncc.getPhoneNumber());
            result = preg.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateNCC(Supplier ncc) {
        boolean result = false;
        try {
            String sql = "UPDATE Supplier SET supplierName = ?, address = ?, phoneNumber = ? WHERE supplierID = ?";

            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, ncc.getSupplierName());
            prep.setString(2, ncc.getAddress());
            prep.setString(3, ncc.getPhoneNumber());
            prep.setString(4, ncc.getSupplierID());

            result = prep.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean deleteNCC(String maNCC) {
        try {
            String sql = "DELETE FROM Supplier WHERE supplierID = ?";
            PreparedStatement preg = ConnectionDatabase.conn.prepareCall(sql);
            preg.setString(1, maNCC);
            return preg.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
