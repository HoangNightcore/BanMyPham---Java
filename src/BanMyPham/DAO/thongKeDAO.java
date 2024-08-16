/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import BanMyPham.BUS.ProductBUS;
import BanMyPham.DTO.Product;
import BanMyPham.DTO.ThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class thongKeDAO {

    public ThongKe getThongKe(int nam) {
        ThongKe thongKe = new ThongKe();
        int[] tongThuQuy = new int[4];
        thongKe.setSoLuongSP(getTongSoLuongSP());
        thongKe.setSoLuongKH(getSoLuongKhachHang());
        thongKe.setSoLuongNV(getSoLuongNhanVien());
        tongThuQuy[0] = getTongThuQuy(nam, 1);
        tongThuQuy[1] = getTongThuQuy(nam, 2);
        tongThuQuy[2] = getTongThuQuy(nam, 3);
        tongThuQuy[3] = getTongThuQuy(nam, 4);
        thongKe.setTongThuQuy(tongThuQuy);
        thongKe.setTopSanPhamBanChay(getTopBanChay());
        return thongKe;
    }

    public ArrayList<Product> getTopBanChay() {
        ArrayList<Product> dssp = new ArrayList<>();
        try {
            String sql = "SELECT TOP 5 productID, SUM(quantity) AS DaBan "
                    + "FROM Order_Details "
                    + "GROUP BY productID "
                    + "ORDER BY DaBan DESC;";
            Statement st = ConnectionDatabase.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ProductBUS spBUS = new ProductBUS();
            while (rs.next()) {
                Product sp = new Product();
                sp.setProductID(rs.getString("productID"));
                sp.setQuantity(rs.getInt("DaBan"));
                sp.setProductName(spBUS.getProductName(sp.getProductID()));
                dssp.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();  // In ra ngoại lệ để gỡ lỗi dễ hơn
        }
        return dssp;
    }

    private int getTongSoLuongSP() {
        try {
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Product");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    private String[] getDateString(int nam, int quy) {
        int namBatDau = nam;
        int namKetThuc = nam;
        String thangBatDau = "01"; //kiểu String do có số 0 ở phía trước
        String thangKetThuc = "04"; //kiểu String do có số 0 ở phía trước
        String[] kq = new String[2];
        switch (quy) {
            case 1:
                thangBatDau = "01";
                thangKetThuc = "04";
                break;
            case 2:
                thangBatDau = "03";
                thangKetThuc = "07";
                break;
            case 3:
                thangBatDau = "06";
                thangKetThuc = "10";
                break;
            case 4:
                thangBatDau = "09";
                thangKetThuc = "01";
                namKetThuc++;
        }
        String strBatDau = Integer.toString(namBatDau) + thangBatDau + "01";
        String strKetThuc = Integer.toString(namKetThuc) + thangKetThuc + "01";
        kq[0] = strBatDau;
        kq[1] = strKetThuc;
        return kq;
    }

    private int getTongThuQuy(int nam, int quy) {
        String[] dateString = getDateString(nam, quy);
        try {
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement("SELECT SUM(totalCost) FROM Orders "
                    + "WHERE orderDate >= ? AND orderDate < ?");
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    private int getSoLuongNhanVien() {
        try {
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Users");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    private int getSoLuongKhachHang() {
        try {
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Customer");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public double getDoanhThuThang(int thang, int nam) {
        try {
            // Xử lý ngày bắt đầu của tháng hiện tại
            String thangBD = String.format("%04d-%02d-01", nam, thang);

            // Xử lý ngày bắt đầu của tháng kế tiếp
            int namKT = thang == 12 ? nam + 1 : nam;
            int thangKT = thang == 12 ? 1 : thang + 1;
            String thangKTStr = String.format("%04d-%02d-01", namKT, thangKT);

            String sql = "SELECT SUM(totalCost) FROM Orders WHERE orderDate >= ? AND orderDate < ?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, thangBD);
            pre.setString(2, thangKTStr);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0; // hoặc giá trị mặc định khác tùy vào yêu cầu của bạn
    }

    public double abc(int thang, int nam) {
        try {
            String d1 = nam + "-" + thang + "-01";
            String d2 = nam + "-" + (thang + 1) + "-01";
            String sql = "SELECT SUM(totalCost) FROM Orders WHERE orderDate BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";

            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, d1);
            pre.setString(2, d2);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }
}
