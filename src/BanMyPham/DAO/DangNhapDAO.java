
package BanMyPham.DAO;

import BanMyPham.DTO.Users;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class DangNhapDAO {

    public Users dangNhap(Users user) {

        try {
            String sql = "SELECT * FROM Users WHERE username=? AND password=?";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            ResultSet rs = pre.executeQuery();
            Users userLogin = null;
            if (rs.next()) { // Kiểm tra xem ResultSet có hàng dữ liệu không
                userLogin = user; //Gán username và password đã có sẵn của user vào userLogin
                userLogin.setUserID(rs.getString("userID"));
//                userLogin.setUsername(rs.getString("username"));
//                userLogin.setPassword(rs.getString("password"));
                userLogin.setFirstName(rs.getString("firstName"));
                userLogin.setLastName(rs.getString("lastName"));
                userLogin.setEmail(rs.getString("email"));
                userLogin.setGender(rs.getString("gender"));
                userLogin.setPhoneNumber(rs.getString("phoneNumber"));
                userLogin.setImageUser(rs.getString("imageUser"));
                userLogin.setAddress(rs.getString("address"));
                userLogin.setUserTypeID(rs.getString("userTypeID"));
            }

            return userLogin;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
