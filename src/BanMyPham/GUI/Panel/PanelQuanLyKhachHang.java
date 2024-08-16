package BanMyPham.GUI.Panel;

import BanMyPham.BUS.CustomerBUS;
import BanMyPham.DTO.Customer;
import Main.Main;
import static Main.Main.changLNF;
import MyCustom.MyTable;
import MyCustom.NumericDocumentFilter;
import MyCustom.TransparentPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

public class PanelQuanLyKhachHang extends javax.swing.JPanel {

    public PanelQuanLyKhachHang() {
        changLNF("Windows");
        initComponents();
        addControls();
        addEvents();
    }
    private CustomerBUS customerBUS = new CustomerBUS();

    final Color colorPanel = new Color(247, 247, 247);
    JButton btnReset;
    JTextField txtID, txtFullName, txtLastName, txtTotalSpending, txtTukhoa, txtMaxChiTieu, txtMinchiTieu, txtPhoneNumber, txtAddress;
    JComboBox<String> cmbGender;
    JButton btnThem, btnSua, btnXoa, btnTim;
    MyTable tblCustomer;
    DefaultTableModel dtmCustomer;

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        int w = 1030;
        int h = 750;

        /*
        =========================================================================
                                    PANEL KHÁCH HÀNG
        =========================================================================
         */
        JPanel pnKhachHang = new TransparentPanel();
        pnKhachHang.setLayout(new BoxLayout(pnKhachHang, BoxLayout.Y_AXIS));

        JPanel pnTopKH = new TransparentPanel();
        pnTopKH.setLayout(new BoxLayout(pnTopKH, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ KHÁCH HÀNG</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnTopKH.add(pnTitle);

        //======PANEL TEXT FIELD=======
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JPanel pnTemp1 = new TransparentPanel();
        pnTemp1.setLayout(new BoxLayout(pnTemp1, BoxLayout.X_AXIS));
        JPanel pnTemp2 = new TransparentPanel();
        pnTemp2.setLayout(new BoxLayout(pnTemp2, BoxLayout.X_AXIS));
        JPanel pnTemp3 = new TransparentPanel();
        pnTemp3.setLayout(new BoxLayout(pnTemp3, BoxLayout.X_AXIS));

        JLabel lblID, lblFirstName, lblLastName, lblGender, lblTongChiTieu, lblPhoneNumber, lblAddress;
        lblID = new JLabel("Mã Khách hàng");
        lblFirstName = new JLabel("FullName");
//        lblLastName = new JLabel("Last Name");
        lblGender = new JLabel("Giới tính");
        lblPhoneNumber = new JLabel("Số điện thoại");
        lblAddress = new JLabel("Địa chỉ");
        lblTongChiTieu = new JLabel("Tổng chi tiêu");

        lblID.setFont(font);
        lblFirstName.setFont(font);
//        lblLastName.setFont(font);
        lblGender.setFont(font);
        lblPhoneNumber.setFont(font);
        lblAddress.setFont(font);
        lblTongChiTieu.setFont(font);

        txtID = new JTextField(15);
        txtID.setEditable(false);
        txtFullName = new JTextField(15);
        txtLastName = new JTextField(15);
        txtTotalSpending = new JTextField(15);
        txtPhoneNumber = new JTextField(15);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào phoneNumber textField
        ((AbstractDocument) txtPhoneNumber.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        txtAddress = new JTextField(15);
        txtTotalSpending.setEditable(false);
        cmbGender = new JComboBox<>();
        cmbGender.addItem("Chọn giới tính");
        cmbGender.addItem("Nam");
        cmbGender.addItem("Nữ");

        txtID.setFont(font);
        txtFullName.setFont(font);
        txtLastName.setFont(font);
        txtPhoneNumber.setFont(font);
        txtAddress.setFont(font);
        txtTotalSpending.setFont(font);
        cmbGender.setFont(font);

        JPanel pnID = new TransparentPanel();
        pnID.add(lblID);
        pnID.add(txtID);
        pnTemp1.add(pnID);

        JPanel pnFirstName = new TransparentPanel();
        pnFirstName.add(lblFirstName);
        pnFirstName.add(txtFullName);
        pnTemp1.add(pnFirstName);

//        JPanel pnLastName = new TransparentPanel();
//        pnLastName.add(lblLastName);
//        pnLastName.add(txtLastName);
//        pnTemp1.add(pnLastName);
        JPanel pnGender = new TransparentPanel();
        pnGender.add(lblGender);
        pnGender.add(cmbGender);
        pnTemp2.add(pnGender);

        JPanel pnPhoneNumber = new TransparentPanel();
        pnPhoneNumber.add(lblPhoneNumber);
        pnPhoneNumber.add(txtPhoneNumber);
        pnTemp2.add(pnPhoneNumber);

        JPanel pnAddress = new TransparentPanel();
        pnAddress.add(lblAddress);
        pnAddress.add(txtAddress);
        pnTemp3.add(pnAddress);

        JPanel pnTongChiTieu = new TransparentPanel();
        pnTongChiTieu.add(lblTongChiTieu);
        pnTongChiTieu.add(txtTotalSpending);
        pnTemp3.add(pnTongChiTieu);

//        Dimension lblSize = lblID.getPreferredSize();
        Dimension lblSize = new Dimension(140, 50);
        lblID.setPreferredSize(lblSize);
        lblFirstName.setPreferredSize(lblSize);
//        lblLastName.setPreferredSize(lblSize);
        lblGender.setPreferredSize(lblSize);
        lblPhoneNumber.setPreferredSize(lblSize);
        lblAddress.setPreferredSize(lblSize);
        lblTongChiTieu.setPreferredSize(lblSize);
        cmbGender.setPreferredSize(txtFullName.getPreferredSize());

        pnTextField.add(pnTemp1);
        pnTextField.add(pnTemp2);
        pnTextField.add(pnTemp3);
        pnTopKH.add(pnTextField);
        pnKhachHang.add(pnTopKH);

        //===============PANEL BUTTON=============
        JPanel pnButton = new TransparentPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);

        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        pnKhachHang.add(pnButton);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        Dimension btnSize = btnThem.getPreferredSize();
        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);

        //====PANEL SEARCH=====
        JPanel pnTimKiem = new TransparentPanel();
        JLabel lblTimKiem = new JLabel("Từ khoá tìm");
        lblTimKiem.setFont(font);
        txtTukhoa = new JTextField(15);
        txtTukhoa.setFont(font);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTukhoa);
        pnKhachHang.add(pnTimKiem);

        JPanel pnTimGioiHan = new TransparentPanel();
        JLabel lblMin = new JLabel("Chi tiêu từ:");
        JLabel lblMax = new JLabel("đến:");
        lblMin.setFont(font);
        lblMax.setFont(font);
        txtMinchiTieu = new JTextField(5);
        txtMinchiTieu.setHorizontalAlignment(JTextField.CENTER);
        txtMaxChiTieu = new JTextField(5);
        txtMaxChiTieu.setHorizontalAlignment(JTextField.CENTER);
        txtMinchiTieu.setFont(font);
        txtMaxChiTieu.setFont(font);
        btnTim = new JButton(new ImageIcon("image/Search-icon.png"));
        btnTim = new JButton(new ImageIcon("image/Search-icon.png"));
        pnTimGioiHan.add(lblMin);
        pnTimGioiHan.add(txtMinchiTieu);
        pnTimGioiHan.add(lblMax);
        pnTimGioiHan.add(txtMaxChiTieu);
        pnTimGioiHan.add(btnTim);
        pnKhachHang.add(pnTimGioiHan);
        //=========================TABLE=====================
        dtmCustomer = new DefaultTableModel();
        dtmCustomer.addColumn("Mã KH");
        dtmCustomer.addColumn("First Name");
        dtmCustomer.addColumn("Last Name");
        dtmCustomer.addColumn("Giới tính");
        dtmCustomer.addColumn("Số điện thoại");
        dtmCustomer.addColumn("Địa chỉ");
        dtmCustomer.addColumn("Tổng chi tiêu");
        dtmCustomer.addColumn("Mã Loại KH");
        dtmCustomer.addColumn("Trạng thái");

        tblCustomer = new MyTable(dtmCustomer);

        JScrollPane scrtblKhachHang = new JScrollPane(tblCustomer);

        this.add(pnKhachHang, BorderLayout.NORTH);
        this.add(scrtblKhachHang, BorderLayout.CENTER);

        loadDataToTableCustomer();
    }

    private void addEvents() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataToTableCustomer();
                txtID.setText("");
                txtFullName.setText("");
                txtLastName.setText("");
                txtPhoneNumber.setText("");
                txtAddress.setText("");
                txtTotalSpending.setText("");
                txtTukhoa.setText("");
                txtMinchiTieu.setText("");
                txtMaxChiTieu.setText("");
                cmbGender.setSelectedIndex(0);
            }
        });

        tblCustomer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClicktblCustomer();
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
                btnThem.setEnabled(false);
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

        txtTukhoa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }
        });

        txtMinchiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaxChiTieu.requestFocus();
            }
        });

        txtMaxChiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTim.doClick();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindToSpendingRange();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddCustomer();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEditCustomer();
                btnSua.setEnabled(false);
                btnXoa.setEnabled(false);
                btnThem.setEnabled(true);
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteCustomer();
                btnSua.setEnabled(false);
                btnXoa.setEnabled(false);
                btnThem.setEnabled(true);
            }
        });
    }

    private void loadDataToTableCustomer() {
        customerBUS.readCustomerList();
        ArrayList<Customer> dskh = customerBUS.getCustomerList();
        loadDataToTableCustomer(dskh);
    }

    private void loadDataToTableCustomer(ArrayList<Customer> dskh) {
        dtmCustomer.setRowCount(0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (Customer customer : dskh) {
            Vector vec = new Vector();
            vec.add(customer.getCustomerID());
            vec.add(customer.getFirstName());
            vec.add(customer.getLastName());
            vec.add(customer.getGender());
            vec.add(customer.getPhoneNumber());
            vec.add(customer.getAddress());
            vec.add(dcf.format(customer.getTotalSpending()));
            vec.add(customer.getCustomerTypeID());
            vec.add(Boolean.toString(customer.isStatus()).toUpperCase());
            dtmCustomer.addRow(vec);
        }
    }

    private void handleClicktblCustomer() {
        int row = tblCustomer.getSelectedRow();
        if (row > -1) {
            txtID.setText(tblCustomer.getValueAt(row, 0) + "");
            txtFullName.setText(tblCustomer.getValueAt(row, 1) + " "+tblCustomer.getValueAt(row, 2) + "");
            int index = tblCustomer.getValueAt(row, 3).equals("Nam") ? 1 : 2;
            cmbGender.setSelectedIndex(index);
            txtPhoneNumber.setText(tblCustomer.getValueAt(row, 4) + "");
            txtAddress.setText(tblCustomer.getValueAt(row, 5) + "");
            txtTotalSpending.setText(tblCustomer.getValueAt(row, 6) + "");
        }
    }

    private void handleFindToSpendingRange() {
        ArrayList<Customer> customerList = customerBUS.findCustomerByTotalSpending(txtMinchiTieu.getText(), txtMaxChiTieu.getText());
        if (customerList == null) {
            return;
        }
        loadDataToTableCustomer(customerList);
    }

    private void xuLyLiveSearch() {
        ArrayList<Customer> dskh = customerBUS.findCustomerByKeyWord(txtTukhoa.getText());
        loadDataToTableCustomer(dskh);
    }

    private void handleAddCustomer() {
        if (customerBUS.addCustomer(txtFullName.getText(), cmbGender.getSelectedItem() + "", txtPhoneNumber.getText(), txtAddress.getText())) {
            btnReset.doClick();
        }
    }

    private void handleEditCustomer() {
        if (customerBUS.editCustomer(txtID.getText(), txtFullName.getText(), cmbGender.getSelectedItem() + "", txtPhoneNumber.getText(), txtAddress.getText())) {
            btnReset.doClick();
        }
    }

    private void handleDeleteCustomer() {
        if (customerBUS.deleteCustomer(txtID.getText())) {
            btnReset.doClick();
        }
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
