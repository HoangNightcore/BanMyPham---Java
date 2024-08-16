package BanMyPham.BUS;

import BanMyPham.DAO.UsersDAO;
import BanMyPham.DTO.Users;
import MyCustom.MyDialog;
import java.util.ArrayList;

public class UsersBUS {

    private UsersDAO usersDAO = new UsersDAO();
    private ArrayList<Users> usersList = null;

    public UsersBUS() {
        readUsersList();
    }

    public void readUsersList() {
        this.usersList = usersDAO.getUsersList();
    }

    public ArrayList<Users> getUserList() {
        if (this.usersList == null) {
            readUsersList();
        }
        return this.usersList;
    }

    public boolean addUser(Users user) {
        if (user.getUsername().trim().equals("")) {
            new MyDialog("Không được để trống Tên đăng nhập!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (kiemTraTrungTenDangNhap(user.getUsername())) {
            new MyDialog("Tên đăng nhập bị trùng!", MyDialog.ERROR_DIALOG);
            return false;
        }

        boolean flag = usersDAO.addUser(user);
        if (flag) {
            new MyDialog("Cấp tài khoản thành công! Mật khẩu là " + user.getPassword(), MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Cấp tài khoản thất bại! Tài khoản đã tồn tại", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean addUser(String username, String password, String firstName, String lastName, String email, String gender, String phoneNumber, String imageUser, String address, String userTypeID) {
        username = username.trim();
        password = password.trim();
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim();
        gender = gender.trim();
        phoneNumber = phoneNumber.trim();
        imageUser = imageUser.trim();
        address = address.trim();
        userTypeID = userTypeID.trim();
        if (username.equals("")) {
            new MyDialog("Username không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (password.equals("")) {
            new MyDialog("Password không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
//        if (userTypeID.equals("")) {
//            new MyDialog("Loại tài khoản không được để trống!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setImageUser(imageUser);
        user.setAddress(address);
        user.setUserTypeID(userTypeID);
        boolean flag = usersDAO.addUser(user);
        if (!flag) {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateUser(String userID, String username, String firstName, String lastName, String email, String gender, String phoneNumber, String imageUser, String address) {
        userID = userID.trim();
        username = username.trim();
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim();
        gender = gender.trim();
        phoneNumber = phoneNumber.trim();
        imageUser = imageUser.trim();
        address = address.trim();
        if (username.equals("")) {
            new MyDialog("Username không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        Users user = new Users();
        user.setUserID(userID);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setImageUser(imageUser);
        user.setAddress(address);
        boolean flag = usersDAO.updateUser(user);
        if (!flag) {
            new MyDialog("Cập nhập thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Cập nhập thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public ArrayList<Users> findUser(String keyWord) {
        keyWord = keyWord.toLowerCase();
        ArrayList<Users> usrLst = new ArrayList<>();
        for (Users user : usersList) {
            if (user.getFirstName().toLowerCase().contains(keyWord) || user.getLastName().toLowerCase().contains(keyWord)
                    || user.getGender().toLowerCase().contains(keyWord)) {
                usrLst.add(user);
            }
        }
        return usrLst;
    }

    public boolean deleteUser(String userID) {
        try {
            userID = userID.trim();
            MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                flag = usersDAO.deleteUser(userID);
                if (flag) {
                    new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
                } else {
                    new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new MyDialog("Chưa chọn nhân viên!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

//    public boolean nhapExcel(String ho, String ten, String gioiTinh, String chucVu) {
//        Users nv = new Users();
//        nv.setHo(ho);
//        nv.setTen(ten);
//        nv.setGioiTinh(gioiTinh);
//        nv.setChucVu(chucVu);
//        boolean flag = usersDAO.nhapExcel(nv);
//        return flag;
//    }
    public String getUsernameByUserID(String userID) {
        userID = userID.trim();
        return usersDAO.getUsernameByID(userID);
    }

    public String getUserTypeIDByUserID(String userID) {
        userID = userID.trim();
        return usersDAO.getUserTypeIDByUserID(userID);
    }

    public void resetPassword(String userID, String username) {
        userID = userID.trim();
        username = username.trim();
        boolean flag = usersDAO.resetPassword(userID, username);
        if (flag) {
            new MyDialog("Đặt lại thành công! Mật khẩu mới là: " + username, MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Đặt lại thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    public void datLaiQuyen(String userID, String userTypeID) {
        userID = userID.trim();
        userTypeID = userTypeID.trim();
        boolean flag = usersDAO.setUserType(userID, userTypeID);
        if (flag) {
            new MyDialog("Đặt lại thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Đặt lại thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    public boolean kiemTraTrungTenDangNhap(String username) {
        return usersDAO.checkTrungTenDangNhap(username);
    }

    public boolean changePassword(String userID, String oldPassword, String newPassword, String rewritePassword) {
        if (!newPassword.equals(rewritePassword)) {
            new MyDialog("Mật khẩu mới không khớp!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = usersDAO.changePassword(userID, oldPassword, newPassword);
        if (flag) {
            new MyDialog("Đổi thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Mật khẩu cũ nhập sai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
