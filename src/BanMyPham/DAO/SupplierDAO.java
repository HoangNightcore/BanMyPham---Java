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
public class SupplierDAO {

    public ArrayList<Supplier> getListNCC() {
        ArrayList<Supplier> NCC = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Supplier";
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Supplier pn = new Supplier();
                pn.setSupplierID(rs.getString(1));
                pn.setSupplierName(rs.getString(2));
                pn.setPhoneNumber(rs.getString(3));
                pn.setAddress(rs.getString(4));
                NCC.add(pn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return NCC;
    }

//    public boolean themNCC(Supplier pn) {
//        boolean result = false;
//        try {
//            String sql = "INSERT INTO Supplier(supplierName, address, phoneNumber) "
//                    + "VALUES(?,?,?)";
//            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
//            prep.setInt(1, pn.getMaNCC());
//            prep.setInt(2, pn.getMaNV());
//            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
//            prep.setInt(4, pn.getTongTien());
//            result = prep.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public PhieuNhap getPhieuNhap(int maPN) {
//        PhieuNhap pn = null;
//        try {
//            String sql = "SELECT * FROM phieunhap WHERE MaPN=" + maPN;
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                pn = new PhieuNhap();
//                pn.setMaPN(rs.getInt(1));
//                pn.setMaNCC(rs.getInt(2));
//                pn.setMaNV(rs.getInt(3));
//                pn.setNgayLap(rs.getDate(4));
//                pn.setTongTien(rs.getInt(5));
//            }
//        } catch (SQLException ex) {
//            return null;
//        }
//        return pn;
//    }
//
//    public boolean deletePhieuNhap(int maPN) {
//        boolean result = false;
//        try {
//            String sql = "DELETE FROM phieunhap WHERE MaPN=" + maPN;
//            Statement stmt = MyConnect.conn.createStatement();
//            result = stmt.executeUpdate(sql) > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public boolean updatePhieuNhap(int maPN, PhieuNhap pn) {
//        boolean result = false;
//        try {
//            String sql = "UPDATE phieunhap SET MaPN=?, MaNCC=?, MaNV=?, NgayLap=?, TongTien=? "
//                    + "WHERE MaPN=" + maPN;
//            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
//            prep.setInt(1, pn.getMaPN());
//            prep.setInt(2, pn.getMaNCC());
//            prep.setInt(3, pn.getMaNV());
//            prep.setDate(4, new java.sql.Date(pn.getNgayLap().getTime()));
//            prep.setInt(5, pn.getTongTien());
//            result = prep.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public int getLastID() {
//        try {
//            String sql = "SELECT MAX(maPN) FROM phieunhap";
//            Statement st = MyConnect.conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
}
