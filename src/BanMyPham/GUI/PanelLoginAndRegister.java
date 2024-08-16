package BanMyPham.GUI;

import BanMyPham.BUS.DangNhapBUS;
import BanMyPham.DTO.Users;
import BanMyPham.GUI.MainGUI;
import Main.Main;
import MyCustom.ButtonLoginAndRegister;
import MyCustom.MyDialog;
import MyCustom.MyPasswordField;
import MyCustom.MyTable;
import MyCustom.MyTextField;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JPanel {

    public Users getUser() {
        return user;
    }

    private MyTextField txtUserLogin = new MyTextField();
    private MyPasswordField txtPasswordLogin = new MyPasswordField();
    private MyTextField txtUserRegister = new MyTextField();
    private MyPasswordField txtPasswordRegister = new MyPasswordField();
    private MyTextField txtEmailRegister = new MyTextField();
    private JCheckBox rememberLogin;
    private Users user;

    public PanelLoginAndRegister(ActionListener eventRegiste) {
        initComponents();
        initRegister(eventRegiste);
        initLogin();
        Login.setVisible(true);
        Register.setVisible(false);
    }

    private void userRememberedHandleInLastLogin() {
        DangNhapBUS dangNhapBUS = new DangNhapBUS();
        String line = dangNhapBUS.getUserRemembered();
        try {
            String[] arr = line.split(" | ");
            txtUserLogin.setText(arr[0]);
            txtPasswordLogin.setText(arr[2]);
            txtUserLogin.requestFocus();
        } catch (Exception e) {
            txtUserLogin.setText("");
            txtPasswordLogin.setText("");
            txtUserLogin.requestFocus();
        }
    }

    private void initRegister(ActionListener eventRegister) {
        //Khởi tạo panel register với layout thuộc định dạng của miglayout  
        Register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push")); //wrap: tự động xuống hàng nếu component đó không thể nằm tiếp trên 1 dòng____push[center] canh giữa theo chiều ngang____push[]25[] khoảng cách ở giữa các dòng là 25 đơn vị
        JLabel label = new JLabel("Tạo Tài Khoản"); //Tạo label làm tiêu đề 
        label.setFont(new Font("sansserif", 1, 30)); //Cài đặt font cho label
        label.setForeground(new Color(7, 164, 121)); //Cài đặt màu nề cho label
        Register.add(label); //Thêm label vào trong register

        txtUserRegister.setPrefixIcon(new ImageIcon("image/LoginUI/user.png"));
        txtUserRegister.setHint("Username");
        Register.add(txtUserRegister, "w 60%");

        txtEmailRegister.setPrefixIcon(new ImageIcon("image/LoginUI/mail.png"));
        txtEmailRegister.setHint("Email");
        Register.add(txtEmailRegister, "w 60%");

        txtPasswordRegister.setPrefixIcon(new ImageIcon("image/LoginUI/pass.png"));
        txtPasswordRegister.setHint("Password");
        Register.add(txtPasswordRegister, "w 60%");

        ButtonLoginAndRegister cmdRegister = new ButtonLoginAndRegister();
        cmdRegister.setBackground(new Color(7, 164, 121));
        cmdRegister.setForeground(new Color(250, 250, 250));
        cmdRegister.addActionListener(eventRegister);
        cmdRegister.setText("Đăng Ký");
        Register.add(cmdRegister, "w 40%, h 40");
        cmdRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                user = new Users();
                user.setUsername(txtUserRegister.getText().trim());
                user.setEmail(txtEmailRegister.getText().trim());
                user.setPassword(String.valueOf(txtPasswordRegister.getPassword()));
            }
        });
    }

    private void initLogin() {
        userRememberedHandleInLastLogin();//Lấy thông tin tài khoản từ lần đăng nhập trước (nếu có) và điền vào trong username, password

        Login.setLayout(new MigLayout("wrap", "push[center]push", "110px[]30[]10[]20[]35[]60[]push"));
        JLabel label = new JLabel("Đăng Nhập"); //Tạo label làm tiêu đề 
        label.setFont(new Font("sansserif", 1, 30)); //Cài đặt font cho label
        label.setForeground(new Color(7, 164, 121)); //Cài đặt màu nề cho label
        Login.add(label);

        txtUserLogin.setPrefixIcon(new ImageIcon("image/LoginUI/mail.png"));
        txtUserLogin.setHint("Username");
        Login.add(txtUserLogin, "w 60%");

        txtPasswordLogin.setPrefixIcon(new ImageIcon("image/LoginUI/pass.png"));
        txtPasswordLogin.setHint("Password");
        Login.add(txtPasswordLogin, "w 60%");

//        Main.changLNF("Windows");
        rememberLogin = new JCheckBox("Ghi nhớ đăng nhập");
        rememberLogin.setFont(new Font("sansserif", Font.BOLD, 10));
        rememberLogin.setOpaque(false);
        rememberLogin.setForeground(Color.black);
        rememberLogin.setFocusPainted(false);  //Tắt viền sáng xung quanh checkbox nếu rememberLogin được chọn
        rememberLogin.setSelected(true); //Mặc định khi khởi tạo form là rememberLogin sẽ được chọn
        Login.add(rememberLogin);
//        Main.changLNF("Nimbus");

        ButtonLoginAndRegister cmdLogin = new ButtonLoginAndRegister();
        cmdLogin.setBackground(new Color(7, 164, 121));
        cmdLogin.setForeground(new Color(250, 250, 250));
        cmdLogin.setText("Đăng Nhập");
        cmdLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginHandle();
            }
        });
        Login.add(cmdLogin, "w 40%, h 40");

        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setBackground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);//Hiển thị nút dưới dạng text
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Set thay đổi ký tự trỏ chuột khi di chuột vào
        Login.add(cmdForget);
        cmdForget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyDialog("Xin liên hệ Admin để giải quyết!", MyDialog.INFO_DIALOG);
            }
        });

    }

    public void showLogin(boolean show) {
        if (show) {
            Register.setVisible(false);
            Login.setVisible(true);
        } else {
            Register.setVisible(true);
            Login.setVisible(false);
        }
    }

    public void loginHandle() {
        DangNhapBUS dangNhapBUS = new DangNhapBUS();
        Users user = dangNhapBUS.getUserLogin(txtUserLogin.getText(), txtPasswordLogin.getText(), rememberLogin.isSelected());

        // Xác định container chứa panel
        javax.swing.JFrame frame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);

        if (user != null) {
            if (user.getUserTypeID() == null) {
                new MyDialog("Xin liên hệ Admin để cấp quyền cho tài khoảnt!", MyDialog.INFO_DIALOG);
                return;
            } else {
                frame.dispose();
                MainGUI main = new MainGUI(user);
                main.showWindow();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        Register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        Login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        add(Login, "card3");

        Register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout RegisterLayout = new javax.swing.GroupLayout(Register);
        Register.setLayout(RegisterLayout);
        RegisterLayout.setHorizontalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        RegisterLayout.setVerticalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        add(Register, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Register;
    // End of variables declaration//GEN-END:variables
}
