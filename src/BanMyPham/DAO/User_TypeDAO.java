package BanMyPham.DAO;

import BanMyPham.DTO.User_Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class User_TypeDAO {

    public ArrayList<User_Type> getUserTypeList() {
        try {
            String sql = "SELECT * FROM UserType order By userTypeID";
            Statement st = ConnectionDatabase.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<User_Type> userTypeList = new ArrayList<>();
            while (rs.next()) {
                User_Type userType = new User_Type();
                userType.setUserTypeID(rs.getString(1));
                userType.setTypeName(rs.getString(2));
                userTypeList.add(userType);
            }
            return userTypeList;
        } catch (Exception e) {
        }
        return null;
    }

    public User_Type getUserType(String userTypeID) {
        try {
            String sql = "SELECT * FROM UserType WHERE userTypeID=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userTypeID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                User_Type userType = new User_Type();
                userType.setUserTypeID(userTypeID);
                userType.setTypeName(rs.getString(2));
                return userType;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String getUserTypeIDByName(String userTypeName) {
        try {
            String sql = "SELECT userTypeID FROM UserType WHERE typeName=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userTypeName);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bạn nên xử lý lỗi hoặc in ra thông báo lỗi để debug
        }
        return "";
    }

    public boolean modifyUserType(User_Type userType) {
        try {
            String sql = "UPDATE UserType SET userTypeID=?,typeName=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userType.getUserTypeID());
            pre.setString(2, userType.getTypeName());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean addUserType(User_Type userType) {
        try {
            String sql = "EXEC  InsertUserType ?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, userType.getTypeName());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean deleteUserType(String userTypeID) {
        try {
            String sql1 = "UPDATE Users SET userTypeID='Default' WHERE userTypeID='" + userTypeID + "'";
            Statement st1 = ConnectionDatabase.conn.createStatement();
            st1.executeUpdate(sql1);
            String sql = "DELETE FROM UserType WHERE userTypeID='" + userTypeID + "'";
            Statement st = ConnectionDatabase.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
