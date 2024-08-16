package BanMyPham.BUS;

import BanMyPham.DAO.CustomerDAO;
import BanMyPham.DTO.Customer;
import MyCustom.MyDialog;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerBUS {

    private ArrayList<Customer> customerList = null;
    private CustomerDAO customerDAO = new CustomerDAO();

    //Kiểm tra SDT
    private static final String PHONE_NUMBER_PATTERN = "^0\\d{9}$";// Định dạng số điện thoại gồm 10 chữ số
    private static final Pattern patternPhoneNumber = Pattern.compile(PHONE_NUMBER_PATTERN);

    public void readCustomerList() {
        this.customerList = customerDAO.getCustomerList();
    }

    public ArrayList<Customer> getCustomerList() {
        if (customerList == null) {
            readCustomerList();
        }
        return customerList;
    }

    public ArrayList<Customer> findCustomerByTotalSpending(String txtMin, String txtMax) {
        if (txtMax.trim().equals("") && txtMin.trim().equals("")) {
            return customerList;
        }
        try {
            ArrayList<Customer> ctmLst = new ArrayList<>();
            txtMin = txtMin.replace(",", "");
            txtMax = txtMax.replace(",", "");
            double min = Double.parseDouble(txtMin);
            double max = Double.parseDouble(txtMax);
            for (Customer customer : customerList) {
                if (customer.getTotalSpending() >= min && customer.getTotalSpending() <= max) {
                    ctmLst.add(customer);
                }
            }
            return ctmLst;
        } catch (Exception e) {
            new MyDialog("Hãy nhập giá trị nguyên phù hợp!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<Customer> findCustomerByKeyWord(String keyWord) {
        keyWord = keyWord.toLowerCase();
        ArrayList<Customer> ctmLst = new ArrayList<>();
        for (Customer customer : customerList) {
            String firstName = customer.getFirstName().toLowerCase();
            String lastName = customer.getLastName().toLowerCase();
            String gender = customer.getGender().toLowerCase();
            if (firstName.contains(keyWord) || lastName.contains(keyWord) || gender.contains(keyWord)) {
                ctmLst.add(customer);
            }
        }
        return ctmLst;
    }

    public Customer findCustomerByID(String id) {
        if (id.equals("")) {
            new MyDialog("Hãy Nhập Mã Khách Hàng!", MyDialog.ERROR_DIALOG);
            return null;
        }
        Customer customer = customerDAO.getCustomer(id);
        if (customer == null) {
            new MyDialog("Không Tìm Thấy Khách Hàng!", MyDialog.ERROR_DIALOG);
            return null;
        } else {
            return customer;
        }
    }

    public boolean addCustomer(String fullName, String gender, String phoneNumber, String address) {
        if (fullName.trim().equals("")) {
            new MyDialog("Không được để trống FullName!", MyDialog.ERROR_DIALOG);
            return false;
        }

//        if (lastName.trim().equals("")) {
//            new MyDialog("Không được để trống lastName!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
        if (gender.equals("Chọn giới tính")) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (phoneNumber.equals("")) {
            new MyDialog("Không được để trống số điện thoại!", MyDialog.ERROR_DIALOG);
            return false;
        } else if (!isValidPhoneNumber(phoneNumber)) {
            new MyDialog("Số điện thoại không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (address.equals("")) {
            new MyDialog("Không được để trống địa chỉ", MyDialog.ERROR_DIALOG);
            return false;
        }
        Customer customer = new Customer();
//        customer.setFirstName(firstName);
//        customer.setLastName(lastName);

        //Nếu trong chuỗi không có ký tự khoảng trắng giữa 2 từ thì mặc định FirstName sẽ là chuỗi đó còn LastName rỗng
        String[] fullNameTemp = fullName.split(" ");
        if (fullNameTemp.length == 1) {
            customer.setFirstName(fullNameTemp[0].trim());
            customer.setLastName("");
        } else {
            customer.setFirstName(fullNameTemp[0].trim());
            customer.setLastName(fullNameTemp[1].trim());
        }

        customer.setGender(gender);
        customer.setTotalSpending(0);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        boolean flag = customerDAO.addCustomer(customer);
        if (flag) {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean editCustomer(String id, String fullName, String gender, String phoneNumber, String address) {
        if (fullName.trim().equals("")) {
            new MyDialog("Không được để trống FullName!", MyDialog.ERROR_DIALOG);
            return false;
        }
//        if (lastName.trim().equals("")) {
//            new MyDialog("Không được để trống lastName!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
        if (gender.equals("Chọn giới tính")) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (phoneNumber.equals("")) {
            new MyDialog("Không được để trống số điện thoại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (address.equals("")) {
            new MyDialog("Không được để trống địa chỉ", MyDialog.ERROR_DIALOG);
            return false;
        }
        Customer customer = new Customer();
//        customer.setFirstName(firstName);
//        customer.setLastName(lastName);

        //Nếu trong chuỗi không có ký tự khoảng trắng giữa 2 từ thì mặc định FirstName sẽ là chuỗi đó còn LastName rỗng
        String[] fullNameTemp = fullName.split(" ");
        if (fullNameTemp.length == 1) {
            customer.setFirstName(fullNameTemp[0].trim());
            customer.setLastName("");
        } else {
            customer.setFirstName(fullNameTemp[0].trim());
            customer.setLastName(fullNameTemp[1].trim());
        }
        customer.setGender(gender);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        boolean flag = customerDAO.updateCustomer(id, customer);
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean deleteCustomer(String id) {//Không thể xóa khách hàng được vì sẽ gây lỗi với đơn hàng
        boolean flag = false;
        try {
            id = id.trim();
            MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
            if (dlg.getAction() == MyDialog.CANCEL_OPTION) {
                return false;
            }
            flag = customerDAO.deleteCustomer(id);
        } catch (Exception e) {
            new MyDialog("Chưa chọn khách hàng!", MyDialog.ERROR_DIALOG);
        }
        if (flag) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = patternPhoneNumber.matcher(phoneNumber);
        return matcher.matches();
    }
}
