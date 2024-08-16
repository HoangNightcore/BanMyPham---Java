package BanMyPham.DAO;

import BanMyPham.DTO.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangKyDAO {

    public boolean addRegisterUser(Users user) {
        int affectedRows = 0;
        try {
            String sql = "EXEC InsertRegisterUser ?, ?, ?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getPassword());

            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Xử lý hoặc in ra thông báo lỗi
        }
        return affectedRows > 0;
    }

    public boolean checkDuplicateUsername(String username) {////////////////////////////////////////////////////////////
        try {
            String sql = "SELECT * FROM Users WHERE username =?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDuplicateEmail(String email) {////////////////////////////////////////////////////////////
        try {
            String sql = "SELECT * FROM Users WHERE email =?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
