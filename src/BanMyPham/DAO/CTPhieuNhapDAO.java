package BanMyPham.DAO;

import BanMyPham.DAO.ConnectionDatabase;
import BanMyPham.DTO.WarehouseReceipt_Details;
import BanMyPham.DTO.WarehouseReceipt_Details;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class CTPhieuNhapDAO {

    public ArrayList<WarehouseReceipt_Details> getListCTPhieuNhap() {
        ArrayList<WarehouseReceipt_Details> dsctpn = new ArrayList<>();
        try {

            String sql = "SELECT * FROM WarehouseReceipt_Details";
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                WarehouseReceipt_Details ctpn = new WarehouseReceipt_Details();
                ctpn.setWarehouseReceiptID(rs.getString(1));
                ctpn.setProductID(rs.getString(2));
                ctpn.setQuantity(rs.getInt(3));
                ctpn.setPrice(rs.getInt(4));
                ctpn.setTotalCost(rs.getInt(5));
                dsctpn.add(ctpn);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dsctpn;
    }

    public boolean addCTPhieuNhap(WarehouseReceipt_Details ctpn) {
        boolean result = false;
        try {
            // Phải Update số lượng SP trong kho
            String sqlUpdateSP = "UPDATE Product SET quantity = quantity + ? , price = 4 * ? WHERE productID = ?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareCall(sqlUpdateSP);
            pre.setInt(1, ctpn.getQuantity());
            pre.setDouble(2, ctpn.getPrice());
            pre.setString(3, ctpn.getProductID());
            pre.executeUpdate();

            String sql = "INSERT INTO WarehouseReceipt_Details VALUES(?,?,?,?,?)";
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, ctpn.getWarehouseReceiptID());
            prep.setString(2, ctpn.getProductID());
            prep.setInt(3, ctpn.getQuantity());
            prep.setDouble(4, ctpn.getPrice());
            prep.setDouble(5, ctpn.getTotalCost());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
