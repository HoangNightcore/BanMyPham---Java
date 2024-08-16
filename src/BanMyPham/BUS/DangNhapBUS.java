/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.DangNhapDAO;
import BanMyPham.DTO.Users;
import MyCustom.MyDialog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 *
 * @author acer
 */
public class DangNhapBUS {

    private final static int EMPTY_ERROR = 1;
    private final static int WRONG_ERROR = 2;
    public static Users userLogin = null;

    public Users getUserLogin(String username, String password, boolean rememberCheck) {
        if (checkLogin(username, password) == EMPTY_ERROR) {
            new MyDialog("Không được để trống thông tin!", MyDialog.ERROR_DIALOG);
            return null;
        } else if (checkLogin(username, password) == WRONG_ERROR) {
            new MyDialog("Thông tin tài khoản hoặc mật khẩu không chính xác", MyDialog.ERROR_DIALOG);
            return null;
        } else {
            rememberLoginHandle(username, password, rememberCheck); //Lưu thông tin đăng nhập nếu người dùng check remember
            new MyDialog("Đăng nhập thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return userLogin;
    }

    public String getUserRemembered() {
        try {
            FileInputStream fis = new FileInputStream("remember.dat");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception e) {
        }
        return "";
    }

    public void rememberLoginHandle(String username, String password, boolean checkRemember) {
        try {
            if (!checkRemember) {
                username = "";
                password = "";
            }
            FileWriter fw = new FileWriter("remember.dat");
            fw.write(username + " | " + password);
            fw.close();
        } catch (Exception e) {
        }
    }

    public int checkLogin(String username, String password) {
        //Loại bỏ các ký tự khoảng trắng trong chuỗi trước khi thực hiện kiểm tra đăng nhập
        username = username.replaceAll("\\s+", "");
        password = password.replaceAll("\\s+", "");

        int result = 0;

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        Users account = dangNhapDAO.dangNhap(user);

        if (username.length() <= 0 || password.length() <= 0) {
            result = EMPTY_ERROR;
        } else if (account == null) {
            result = WRONG_ERROR;
        }

        userLogin = account;
        return result;
    }
}
