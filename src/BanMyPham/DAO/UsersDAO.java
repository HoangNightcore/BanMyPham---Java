package BanMyPham.DAO;

import BanMyPham.DTO.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDAO {

    public ArrayList<Users> getUsersList() {
        try {
            String sql = "SELECT * FROM Users";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<Users> usersList = new ArrayList<>();
            while (rs.next()) {
                Users user = new Users();

                user.setUserID(rs.getString(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setGender(rs.getString(7));
                user.setPhoneNumber(rs.getString(8));
                user.setImageUser(rs.getString(9));
                user.setAddress(rs.getString(10));
                user.setUserTypeID(rs.getString(11));

                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
        }

        return null;
    }

    public Users getUser(String userID) {
        Users user = null;
        try {
            String sql = "SELECT * FROM Users WHERE userID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(0, userID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                user = new Users();
                user.setUserID(rs.getString(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setGender(rs.getString(7));
                user.setPhoneNumber(rs.getString(8));
                user.setImageUser(rs.getString(9));
                user.setAddress(rs.getString(10));
                user.setUserTypeID(rs.getString(11));
            }
        } catch (SQLException e) {
            return null;
        }

        return user;
    }

    public boolean updateUser(Users user) {
        boolean result = false;
        try {
            String sql = "UPDATE Users SET username=?, firstName=?, lastName=?, email=?, gender=?, phoneNumber=?, imageUser=?, address=? WHERE userID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getFirstName());
            pre.setString(3, user.getLastName());
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getGender());
            pre.setString(6, user.getPhoneNumber());
            pre.setString(7, user.getImageUser());
            pre.setString(8, user.getAddress());
            pre.setString(9, user.getUserID());

            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteUser(String userID) {
        boolean result = false;
        try {
            String sql = "DELETE FROM Users WHERE userID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userID);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean addUser(Users user) {
        int affectedRows = 0;
        try {
            String sql = "EXEC InsertUser ?, ?, ?, ?, ?, ?, ?, ?, ?,?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getFirstName());
            pre.setString(4, user.getLastName());
            pre.setString(5, user.getEmail());
            pre.setString(6, user.getGender());
            pre.setString(7, user.getPhoneNumber());
            pre.setString(8, user.getImageUser());
            pre.setString(9, user.getAddress());
            pre.setString(10, user.getUserTypeID());
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Xử lý hoặc in ra thông báo lỗi
        }
        return affectedRows > 0;
    }
//    public boolean nhapExcel(Users nv) {
//        try {
//            String sql = "DELETE * FROM NhanVien; "
//                    + "INSERT INTO NhanVien(Ho, Ten, GioiTinh, ChucVu) "
//                    + "VALUES(?, ?, ?, ?)";
//            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
//            pre.setString(1, nv.getHo());
//            pre.setString(2, nv.getTen());
//            pre.setString(3, nv.getGioiTinh());
//            pre.setString(4, nv.getChucVu());
//            return true;
//        } catch (SQLException ex) {
//        }
//        return false;
//    }

    public boolean checkTrungTenDangNhap(String username) {////////////////////////////////////////////////////////////
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

    public String getUsernameByID(String userID) {
        try {
            String sql = "SELECT username FROM Users WHERE userID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String getUserTypeIDByUserID(String userID) {
        try {
            String sql = "SELECT userTypeID FROM Users WHERE userID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean resetPassword(String userID, String username) {
        try {
            String sql = "UPDATE Users SET password=? WHERE userID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, userID);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean setUserType(String userID, String userTypeID) {
        try {
            String sql = "UPDATE Users SET userTypeID=? WHERE userID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userTypeID);
            pre.setString(2, userID);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean changePassword(String userID, String oldPassword, String newPassword) {
        try {
            String sql = "UPDATE Users SET password=? WHERE userID=? AND password=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, newPassword);
            pre.setString(2, userID);
            pre.setString(3, oldPassword);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
}
