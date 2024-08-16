package BanMyPham.DAO;

import BanMyPham.DTO.Order_Details;
import BanMyPham.DTO.Order_Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Order_StatusDAO {

    public ArrayList<Order_Status> getOrderStatusList() {
        ArrayList<Order_Status> ordStatusList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Order_Status";
            Statement stmt = ConnectionDatabase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order_Status ordStatus = new Order_Status();
                ordStatus.setOrderStatusID(rs.getString(1));
                ordStatus.setOrderStatusName(rs.getString(2));
            }
        } catch (SQLException ex) {
        }
        return ordStatusList;
    }

    public String getOrderStatusListByStatusName(String statusName) {
        try {
            String sql = "SELECT orderStatusID FROM Order_Status WHERE orderStatusName=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, statusName);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }
}
