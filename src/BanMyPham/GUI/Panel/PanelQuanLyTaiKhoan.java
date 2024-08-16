package BanMyPham.GUI.Panel;

import BanMyPham.BUS.User_TypeBUS;
import BanMyPham.BUS.UsersBUS;
import BanMyPham.DTO.User_Type;
import BanMyPham.DTO.Users;
import BanMyPham.GUI.Dialog.DlgAddUserType;
import BanMyPham.GUI.Dialog.DlgCapTaiKhoan;
import BanMyPham.GUI.Dialog.DlgUserType_Password;
import static Main.Main.changLNF;
import MyCustom.MyDialog;
import MyCustom.MyFileChooser;
import MyCustom.MyTable;
import MyCustom.NumericDocumentFilter;
import MyCustom.TransparentPanel;
import MyCustom.XuLyFileExcel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

public class PanelQuanLyTaiKhoan extends javax.swing.JPanel {

    public PanelQuanLyTaiKhoan() {
        changLNF("Windows");
//        initComponents();
        addControlsNhanVien();
        addEventsUsers();
//        addEventsPhanQuyen();
    }

    private User_TypeBUS userTypeBUS = new User_TypeBUS();
    private UsersBUS usersBUS = new UsersBUS();

    JLabel lblTabbedUser;
    final ImageIcon tabbedSelected = new ImageIcon("image/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/ManagerUI/tabbed-btn.png");
    final Color colorPanel = new Color(247, 247, 247);
    CardLayout cardUsersGroup = new CardLayout();
    JPanel pnCardTabUsers;
    JTextField txtUserID, txtUsername, txtFullName, txtEmail, txtPhoneNumber, txtAddress, txtChucVu, txtFindUser;
    JComboBox<String> cmbGioiTinh;
    MyTable tblUsers;
    DefaultTableModel dtmUsers;
    JButton btnReset, btnUserTypeList, btnEditUser, btnDeleteUser, btnFindUser, btnCapTaiKhoan, btnResetMatKhau, btnXoaTaiKhoan, btnXuatExcel, btnNhapExcel, btnChooseImage;
    JLabel lblUserImage;
    File fileImageUser;

    //Kiểm tra email
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);

    //Kiểm tra SDT
    private static final String PHONE_NUMBER_PATTERN
            = "^\\d{10}$"; // Định dạng số điện thoại gồm 10 chữ số
    private static final Pattern patternPhoneNumber = Pattern.compile(PHONE_NUMBER_PATTERN);

    private void addControlsNhanVien() {
        this.setLayout(new BorderLayout());
//        this.setBackground(colorPanel);
        int w = 1030;
        int h = 750;
        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new TransparentPanel();
        //<editor-fold defaultstate="collapsed" desc="Panel Tab Users & UserType">
        Font font = new Font("", Font.PLAIN, 15);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedUser = new JLabel("Users");
        lblTabbedUser.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedUser.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedUser.setIcon(tabbedSelected);
        lblTabbedUser.setBounds(2, 2, 140, 37);
        lblTabbedUser.setFont(font);
        lblTabbedUser.setForeground(Color.white);
        lblTabbedUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedUser);
        //</editor-fold>

        /*
        =========================================================================
                                    PANEL Users
        =========================================================================
         */
        //<editor-fold defaultstate="collapsed" desc="Panel Users">
        JPanel pnUsers = new TransparentPanel();
        pnUsers.setLayout(new BoxLayout(pnUsers, BoxLayout.Y_AXIS));

        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));
        Component horizontalStrut = Box.createHorizontalStrut(-130);

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ USERS</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnUsers.add(pnTitle);
//        pnTopNV.setBackground(Color.DARK_GRAY);
        //==========

        JPanel pnText = new TransparentPanel();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));

        JPanel temp1, temp2, temp3, temp4;

        txtUserID = new JTextField(15);
        txtUserID.setEditable(false);
        txtUsername = new JTextField(15);
        txtFullName = new JTextField(15);
        txtEmail = new JTextField(15);
        txtPhoneNumber = new JTextField(15);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào txtPhoneNumber textField
        ((AbstractDocument) txtPhoneNumber.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        txtAddress = new JTextField(15);
        cmbGioiTinh = new JComboBox<>();
        txtChucVu = new JTextField(15);
        txtChucVu.setEditable(false);

        txtUserID.setFont(font);
        txtUsername.setFont(font);
        txtFullName.setFont(font);
        txtEmail.setFont(font);
        txtPhoneNumber.setFont(font);
        txtAddress.setFont(font);
        cmbGioiTinh.setFont(font);
        txtChucVu.setFont(font);

        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        JLabel lblID, lblUsername, lblFullName, lblEmail, lblPhoneNumber, lblAddress, lblGioiTinh, lblChucVu;

        lblID = new JLabel("Mã Users");
        lblUsername = new JLabel("Username");
        lblFullName = new JLabel("Full Name");
        lblEmail = new JLabel("Email");
        lblPhoneNumber = new JLabel("SĐT");
        lblAddress = new JLabel("Address");
        lblGioiTinh = new JLabel("Giới tính");
        lblChucVu = new JLabel("Chức vụ");

        lblID.setFont(font);
        lblUsername.setFont(font);
        lblFullName.setFont(font);
        lblEmail.setFont(font);
        lblPhoneNumber.setFont(font);
        lblAddress.setFont(font);
        lblGioiTinh.setFont(font);
        lblChucVu.setFont(font);

        JPanel pnID = new TransparentPanel();
        pnID.add(lblID);
        pnID.add(txtUserID);
//        pnText.add(pnID);

        JPanel pnUsername = new TransparentPanel();
        pnUsername.add(lblUsername);
        pnUsername.add(txtUsername);
//        pnText.add(pnUsername);
        temp1 = new TransparentPanel();
        temp1.setLayout(new BoxLayout(temp1, BoxLayout.X_AXIS));
        temp1.add(pnID);
        temp1.add(pnUsername);

        JPanel pnFullName = new TransparentPanel();
        pnFullName.add(lblFullName);
        pnFullName.add(txtFullName);
//        pnText.add(pnFullName);

        JPanel pnEmail = new TransparentPanel();
        pnEmail.add(lblEmail);
        pnEmail.add(txtEmail);
//        pnText.add(pnEmail);
        temp2 = new TransparentPanel();
        temp2.setLayout(new BoxLayout(temp2, BoxLayout.X_AXIS));
        temp2.add(pnFullName);
        temp2.add(pnEmail);

        JPanel pnGioiTinh = new TransparentPanel();
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(cmbGioiTinh);
//        pnText.add(pnGioiTinh);

        JPanel pnPhoneNumber = new TransparentPanel();
        pnPhoneNumber.add(lblPhoneNumber);
        pnPhoneNumber.add(txtPhoneNumber);
//        pnText.add(pnPhoneNumber);
        temp3 = new TransparentPanel();
        temp3.setLayout(new BoxLayout(temp3, BoxLayout.X_AXIS));
        temp3.add(pnGioiTinh);
        temp3.add(pnPhoneNumber);

        JPanel pnAddress = new TransparentPanel();
        pnAddress.add(lblAddress);
        pnAddress.add(txtAddress);
//        pnText.add(pnAddress);

        JPanel pnChucVu = new TransparentPanel();
        pnChucVu.add(lblChucVu);
        pnChucVu.add(txtChucVu);
//        pnText.add(pnChucVu);
        temp4 = new TransparentPanel();
        temp4.setLayout(new BoxLayout(temp4, BoxLayout.X_AXIS));
        temp4.add(pnAddress);
        temp4.add(pnChucVu);

        //==============================
        pnText.setPreferredSize(new Dimension((int) pnText.getPreferredSize().getWidth() + 200, 300));
        pnText.add(temp1);
        pnText.add(temp2);
        pnText.add(temp3);
        pnText.add(temp4);
        //==============================

//        Dimension lblSize = lblMa.getPreferredSize();
        Dimension lblSize = new Dimension(75, 20);
        lblID.setPreferredSize(lblSize);
        lblUsername.setPreferredSize(lblSize);
        lblFullName.setPreferredSize(lblSize);
        lblEmail.setPreferredSize(lblSize);
        lblPhoneNumber.setPreferredSize(lblSize);
        lblAddress.setPreferredSize(lblSize);
        lblGioiTinh.setPreferredSize(lblSize);
        lblChucVu.setPreferredSize(lblSize);
        cmbGioiTinh.setPreferredSize(txtChucVu.getPreferredSize());

        //==========
        JPanel pnTimNV = new TransparentPanel();
        JLabel lblTim = new JLabel("Từ khoá tìm");
        lblTim.setFont(font);
        txtFindUser = new JTextField(15);
        txtFindUser.setFont(font);
        pnTimNV.add(lblTim);
        pnTimNV.add(txtFindUser);
        pnText.add(pnTimNV);
        lblTim.setPreferredSize(new Dimension(85, 20));

        //==============
        pnThongTin.add(pnText);
        //=================PANEL ẢNH==========
        JPanel pnAnh = new TransparentPanel();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));

        JPanel pnImage = new TransparentPanel();
        pnImage.setPreferredSize(new Dimension((int) pnAnh.getPreferredSize().getWidth(), 250));
        lblUserImage = new JLabel();
        lblUserImage.setPreferredSize(new Dimension(200, 200));
        lblUserImage.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblUserImage.setIcon(getImageUser(""));
        pnImage.add(lblUserImage);
        pnAnh.add(pnImage);

        JPanel pnButtonImage = new TransparentPanel();
        pnButtonImage.setPreferredSize(new Dimension(
                (int) pnImage.getPreferredSize().getHeight(), 40));
        btnChooseImage = new JButton("Chọn ảnh");
        btnChooseImage.setFont(font);
        pnButtonImage.add(btnChooseImage);
        pnImage.add(pnButtonImage);

        pnThongTin.add(pnAnh);
        //==========
        JPanel pnButton = new TransparentPanel();

        btnEditUser = new JButton("Lưu");
        btnDeleteUser = new JButton("Xoá");
        btnFindUser = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnEditUser.setFont(fontButton);
        btnDeleteUser.setFont(fontButton);
        btnFindUser.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnEditUser.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnDeleteUser.setIcon(new ImageIcon("image/delete-icon.png"));
        btnFindUser.setIcon(new ImageIcon("image/Search-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

//        pnButton.add(btnUserTypeList);
        pnButton.add(btnEditUser);
        pnButton.add(btnDeleteUser);
        pnButton.add(btnFindUser);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);

        Dimension btnSize = btnFindUser.getPreferredSize();
//        btnAddUser.setPreferredSize(btnSize);
        btnEditUser.setPreferredSize(btnSize);
        btnDeleteUser.setPreferredSize(btnSize);
        btnFindUser.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);

        JPanel pnButton2 = new TransparentPanel();
        btnCapTaiKhoan = new JButton("Cấp tài khoản");
        btnResetMatKhau = new JButton("Mật khẩu/Quyền");
        btnUserTypeList = new JButton("Xem DS Loại");
//        btnXoaTaiKhoan = new JButton("Khoá tài khoản");
        btnCapTaiKhoan.setIcon(new ImageIcon("image/icons8_man_with_key_32px.png"));
        btnResetMatKhau.setIcon(new ImageIcon("image/icons8_password_reset_32px.png"));
        btnUserTypeList.setIcon(new ImageIcon("image/add-icon.png"));
//        btnXoaTaiKhoan.setIcon(new ImageIcon("image/icons8_denied_32px.png"));
        btnCapTaiKhoan.setFont(fontButton);
        btnResetMatKhau.setFont(fontButton);
        btnUserTypeList.setFont(fontButton);
//        btnXoaTaiKhoan.setFont(fontButton);
        pnButton2.add(btnCapTaiKhoan);
        pnButton2.add(btnResetMatKhau);
        pnButton2.add(btnUserTypeList);
//        pnButton2.add(btnXoaTaiKhoan);

        pnUsers.add(pnThongTin);
        pnUsers.add(pnButton);
        pnUsers.add(pnButton2);
        //===================TABLE Users=====================
        JPanel pnTableUsers = new TransparentPanel();
        pnTableUsers.setLayout(new BorderLayout());
        dtmUsers = new DefaultTableModel();
        dtmUsers.addColumn("Mã User");
        dtmUsers.addColumn("Username");
        dtmUsers.addColumn("FirstName");
        dtmUsers.addColumn("LastName");
        dtmUsers.addColumn("Email");
        dtmUsers.addColumn("Gender");
        dtmUsers.addColumn("PhoneNumber");
        dtmUsers.addColumn("ImageUser");
        dtmUsers.addColumn("Address");
        dtmUsers.addColumn("UserType");
        tblUsers = new MyTable(dtmUsers);
        JScrollPane scrTblUsers = new JScrollPane(tblUsers);
        pnTableUsers.add(scrTblUsers, BorderLayout.CENTER);
        pnUsers.add(scrTblUsers);
        //</editor-fold>

        //========================
        pnCardTabUsers = new JPanel(cardUsersGroup);
        pnCardTabUsers.add(pnUsers, "1");
        this.add(pnCardTabUsers);

        loadDataTblUser();
    }

    private void addEventsUsers() {
        lblTabbedUser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedUser.setIcon(tabbedSelected);
                cardUsersGroup.show(pnCardTabUsers, "1");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });


        tblUsers.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClickTblUser();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        txtFindUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindUser();
            }
        });

        btnFindUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindUser();
            }
        });

        btnUserTypeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserTypeList();
            }
        });
        btnEditUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEditUser();
            }
        });
        btnChooseImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChooseImage();
            }
        });

        btnDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteUser();
            }
        });

        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyXuatExcel();
            }
        });

        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyNhapExcel();
            }
        });

        btnCapTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCapTaiKhoan();
            }
        });

        btnResetMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleResetPassword();
            }
        });

//        btnXoaTaiKhoan.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                xuLyKhoaTaiKhoan();
//            }
//        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage("");
                loadDataTblUser();
                txtUserID.setText("");
                txtUsername.setText("");
                txtFullName.setText("");
                txtEmail.setText("");
                txtPhoneNumber.setText("");
                txtAddress.setText("");
                txtChucVu.setText("");
                txtFindUser.setText("");
                cmbGioiTinh.setSelectedIndex(0);
            }
        });
    }

    private void handleResetPassword() {
        String userID = txtUserID.getText();
        if (userID.trim().equals("")) {
            new MyDialog("Hãy chọn nhân viên!", MyDialog.ERROR_DIALOG);
            return;
        }
        DlgUserType_Password dialog = new DlgUserType_Password(userID);
        dialog.setVisible(true);
        usersBUS.readUsersList();
        loadDataTblUser();
    }

//    private void xuLyNhapExcel() {
//        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
//        if (dlg.getAction() != MyDialog.OK_OPTION) {
//            return;
//        }
//
//        XuLyFileExcel nhapExcel = new XuLyFileExcel();
//        nhapExcel.nhapExcel(tblNhanVien);
//
//        int row = tblNhanVien.getRowCount();
//        for (int i = 0; i < row; i++) {
//            String ho = tblNhanVien.getValueAt(i, 1) + "";
//            String ten = tblNhanVien.getValueAt(i, 2) + "";
//            String gioiTinh = tblNhanVien.getValueAt(i, 3) + "";
//            String chucVu = tblNhanVien.getValueAt(i, 4) + "";
//
//            nhanVienBUS.nhapExcel(ho, ten, gioiTinh, chucVu);
//
//        }
//    }
//    private void xuLyXuatExcel() {
//        XuLyFileExcel xuatExcel = new XuLyFileExcel();
//        xuatExcel.xuatExcel(tblNhanVien);
//    }
    private void handleDeleteUser() {
        String ma = txtUserID.getText();
        boolean flag = usersBUS.deleteUser(ma);
        if (flag) {
            usersBUS.readUsersList();
            loadDataTblUser();
        }
    }

    private void handleEditUser() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return;
        }
        String userID = txtUserID.getText();
        String username = txtUsername.getText();
        String[] fullName = txtFullName.getText().split(" ");
        String firstName = "";
        String lastName = "";

        if (fullName.length >= 1) {
            firstName = fullName[0];
            if (fullName.length > 1) {
                lastName = String.join(" ", Arrays.copyOfRange(fullName, 1, fullName.length));
            }
        }
        String email = txtEmail.getText();
        String gender = cmbGioiTinh.getSelectedItem() + "";
        String phoneNumber = txtPhoneNumber.getText();
        String image = fileImageUser.getName();
        String address = txtAddress.getText();
        String chucVu = txtChucVu.getText();
        if (usersBUS.updateUser(userID, username, firstName, lastName, email, gender, phoneNumber, image, address)) {
            usersBUS.readUsersList();
            loadDataTblUser();
        }
    }

    private void handleCapTaiKhoan() {
        if (txtUsername.getText().trim().equals("")) {
            new MyDialog("Username không được để trống!", MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtFullName.getText().trim().equals("")) {
            new MyDialog("FullName không được để trống!", MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtEmail.getText().trim().equals("")) {
            new MyDialog("Email không được để trống!", MyDialog.ERROR_DIALOG);
            return;
        } else if (!isValidEmail(txtEmail.getText().trim())) {
            new MyDialog("Email không hợp lệ!", MyDialog.ERROR_DIALOG);
            return;
        }
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtPhoneNumber.getText().trim().equals("")) {
            new MyDialog("PhoneNumber không được để trống!", MyDialog.ERROR_DIALOG);
            return;
        } else if (!isValidPhoneNumber(txtPhoneNumber.getText().trim())) {
            new MyDialog("PhoneNumber không hợp lệ!", MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtAddress.getText().trim().equals("")) {
            new MyDialog("Address không được để trống!", MyDialog.ERROR_DIALOG);
            return;
        }

        Users user = new Users();
        user.setUsername(txtUsername.getText().trim());

        //Nếu trong chuỗi không có ký tự khoảng trắng giữa 2 từ thì mặc định FirstName sẽ là chuỗi đó còn LastName rỗng
        String[] fullName = txtFullName.getText().split(" ");
        if (fullName.length == 1) {
            user.setFirstName(fullName[0].trim());
            user.setLastName("");
        } else {
            user.setFirstName(fullName[0].trim());
            user.setLastName(fullName[1].trim());
        }

        user.setEmail(txtEmail.getText().trim());
        user.setGender(cmbGioiTinh.getSelectedItem() + "");
        user.setPhoneNumber(txtPhoneNumber.getText().trim());
        user.setImageUser(fileImageUser.getName().trim());
        user.setAddress(txtAddress.getText().trim());

        String userTypeName = txtChucVu.getText();
        user.setUserTypeID(userTypeBUS.getUserTypeIDByName(userTypeName).trim());
//        if (usersBUS.addUser(username, "", firstName, lastName, email, gender, phoneNumber, image, address, userTypeID)) {
//            usersBUS.readUsersList();
//            loadDataTblNhanVien();
//        }
        DlgCapTaiKhoan dialog = new DlgCapTaiKhoan(user);
        dialog.setVisible(true);
        usersBUS.readUsersList();
        loadDataTblUser();
    }

    private void handleFindUser() {
        ArrayList<Users> dsnv = usersBUS.findUser(txtFindUser.getText());
        dtmUsers.setRowCount(0);
        for (Users nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getUserID());
            vec.add(nv.getUsername());
            vec.add(nv.getFirstName());
            vec.add(nv.getLastName());
            vec.add(nv.getEmail());
            vec.add(nv.getGender());
            vec.add(nv.getPhoneNumber());
            vec.add(nv.getImageUser());
            vec.add(nv.getAddress());
            userTypeBUS.getUserType(nv.getUserTypeID());
            User_Type temp = userTypeBUS.userType;
            vec.add(temp.getTypeName());
            dtmUsers.addRow(vec);
        }
    }

    private void handleClickTblUser() {
        int row = tblUsers.getSelectedRow();
        if (row > -1) {
            txtUserID.setText(tblUsers.getValueAt(row, 0) + "");
            txtUsername.setText(tblUsers.getValueAt(row, 1) + "");
            txtFullName.setText(tblUsers.getValueAt(row, 2) + " " + tblUsers.getValueAt(row, 3) + "");
            txtEmail.setText(tblUsers.getValueAt(row, 4) + "");
            txtPhoneNumber.setText(tblUsers.getValueAt(row, 6) + "");
            txtAddress.setText(tblUsers.getValueAt(row, 8) + "");
            txtChucVu.setText(tblUsers.getValueAt(row, 9) + "");

            String gioiTinh = "";
            if ((tblUsers.getValueAt(row, 5) + "").equals("Nam")) {
                cmbGioiTinh.setSelectedIndex(1);
            } else {
                cmbGioiTinh.setSelectedIndex(2);
            }
        }
        loadImage("image/User/" + tblUsers.getValueAt(row, 7) + "");
    }
//

    private void loadDataTblUser() {
        dtmUsers.setRowCount(0);
        ArrayList<Users> usersList = usersBUS.getUserList();

        for (Users nv : usersList) {
            Vector vec = new Vector();
            vec.add(nv.getUserID());
            vec.add(nv.getUsername());
            vec.add(nv.getFirstName());
            vec.add(nv.getLastName());
            vec.add(nv.getEmail());
            vec.add(nv.getGender());
            vec.add(nv.getPhoneNumber());
            vec.add(nv.getImageUser());
            vec.add(nv.getAddress());
            if (nv.getUserTypeID() == null) {
                vec.add("");
            } else {
                userTypeBUS.getUserType(nv.getUserTypeID());
                User_Type temp = userTypeBUS.userType;
                vec.add(temp.getTypeName());
            }
//            System.out.println(temp.get());
            dtmUsers.addRow(vec);
        }
    }

    private void loadImage(String anh) {
        lblUserImage.setIcon(getImageUser(anh));
    }

    private void saveFileImage() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileImageUser.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("image/User/" + fileImageUser.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void handleChooseImage() {
        JFileChooser fileChooser = new MyFileChooser("image/User/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileImageUser = fileChooser.getSelectedFile();
            lblUserImage.setIcon(getImageUser(fileImageUser.getPath()));
        }
    }
//    TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();

    private ImageIcon getImageUser(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/User/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileImageUser = new File(src);
        } catch (IOException e) {
            fileImageUser = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = patternPhoneNumber.matcher(phoneNumber);
        return matcher.matches();
    }
    private void loadUserTypeList(){
        DlgAddUserType dlg=new DlgAddUserType();
        dlg.setVisible(true);
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
