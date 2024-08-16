
package BanMyPham.DAO;

import BanMyPham.DTO.Customer;
import MyCustom.MyDialog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerDAO {

    public ArrayList<Customer> getCustomerList() {
        try {
            String sql = "SELECT * FROM Customer";
            PreparedStatement pre = ConnectionDatabase.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<Customer> customerList = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getString(1));
                customer.setFirstName(rs.getString(2));
                customer.setLastName(rs.getString(3));
                customer.setGender(rs.getString(4));
                customer.setPhoneNumber(rs.getString(5));
                customer.setAddress(rs.getString(6));
                customer.setTotalSpending(rs.getDouble(7));
                customer.setCustomerTypeID(rs.getString(8));
                customer.setStatus(rs.getBoolean(9));
                customerList.add(customer);
            }
            return customerList;
        } catch (SQLException ex) {
        }
        return null;
    }
    

    public Customer getCustomer(String customerID) {
        Customer customer = null;
        try {
            String sql = "SELECT * FROM Customer WHERE customerID=?";
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, customerID);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setCustomerID(rs.getString(1));
                customer.setFirstName(rs.getString(2));
                customer.setLastName(rs.getString(3));
                customer.setGender(rs.getString(4));
                customer.setPhoneNumber(rs.getString(5));
                customer.setAddress(rs.getString(6));
                customer.setTotalSpending(rs.getDouble(7));
                customer.setCustomerTypeID(rs.getString(8));
                customer.setStatus(rs.getBoolean(9));
            }
        } catch (SQLException ex) {
            return null;
        }
        return customer;
    }

    public boolean addCustomer(Customer customer) {
        boolean result = false;
        try {
            String sql = "EXEC InsertCustomer ?,?,?,?,?,?,?";
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, customer.getFirstName());
            prep.setString(2, customer.getLastName());
            prep.setString(3, customer.getGender());
            prep.setString(4, customer.getPhoneNumber());
            prep.setString(5, customer.getAddress());
            prep.setDouble(6, customer.getTotalSpending());
            prep.setString(7, customer.getCustomerTypeID());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteCustomer(String customerID) {
        try {
            // Truy vấn để lấy ra giá trị status
            String sqlTest = "SELECT status FROM Customer WHERE customerID=?";
            PreparedStatement prepTest = ConnectionDatabase.conn.prepareStatement(sqlTest);
            prepTest.setString(1, customerID);
            ResultSet rs = prepTest.executeQuery();

            // Kiểm tra xem có dữ liệu trả về hay không
            if (rs.next()) {
                boolean status = rs.getBoolean("status");

            if(status){
//                MyDialog warn= new MyDialog("Khách hàng này không thể xóa được", MyDialog.WARNING_DIALOG);
                return false;          
                } else {
                    // Tiến hành xóa khách hàng
                    String sqlDelete = "DELETE FROM Customer WHERE customerID=?";
                    PreparedStatement prepDelete = ConnectionDatabase.conn.prepareStatement(sqlDelete);
                    prepDelete.setString(1, customerID);
                    return prepDelete.executeUpdate() > 0;
                }
            } else {
                // Không tìm thấy khách hàng với ID tương ứng
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(String customerID, Customer customer) {
        boolean result = false;
        try {
            String sql = "UPDATE Customer SET firstName=?, lastName=?, gender=?, phoneNumber=?, address=?, totalSpending=?, customerTypeID=? WHERE customerID=?";
            PreparedStatement prep = ConnectionDatabase.conn.prepareStatement(sql);
            prep.setString(1, customer.getFirstName());
            prep.setString(2, customer.getLastName());
            prep.setString(3, customer.getGender());
            prep.setString(4, customer.getPhoneNumber());
            prep.setString(5, customer.getAddress());
            prep.setDouble(6, customer.getTotalSpending());
            prep.setString(7, customer.getCustomerTypeID());
            prep.setString(8, customerID);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateTotalSpending(String customerID, double totalSpending) {
        boolean result = false;
        try {
            String sql = "UPDATE Customer SET totalSpending=" + totalSpending + " WHERE MaKH=" + customerID;
            Statement stmt = ConnectionDatabase.conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
