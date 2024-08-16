package BanMyPham.GUI.Dialog;

import BanMyPham.BUS.CustomerBUS;
import BanMyPham.DTO.Customer;
import MyCustom.MyTable;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DlgTimKhach extends JDialog {

    private CustomerBUS khachHangBUS = new CustomerBUS();
    public static Customer khachHangTimDuoc = null;

    public DlgTimKhach() {
        addControls();
        addEvents();

        this.setSize(1000, 600);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblKhachHang;
    private DefaultTableModel dtmKhachHang;
    private JButton btnChon, btnThemKhach;

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khoá tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Mã KH");
        dtmKhachHang.addColumn("First Name");
        dtmKhachHang.addColumn("Last Name");
        dtmKhachHang.addColumn("Gender");
        dtmKhachHang.addColumn("Phone Number");
        dtmKhachHang.addColumn("Address");
        dtmKhachHang.addColumn("Total Spending");
        dtmKhachHang.addColumn("CustomerTypeID");
        tblKhachHang = new MyTable(dtmKhachHang);

        TableColumnModel columnModelCustomer = tblKhachHang.getColumnModel();
        columnModelCustomer.getColumn(4).setPreferredWidth(100);
        columnModelCustomer.getColumn(5).setPreferredWidth(140);
        JScrollPane scrKhachHang = new JScrollPane(tblKhachHang);
        pnTable.add(scrKhachHang, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThemKhach = new JButton("Thêm khách");
        btnChon.setFont(font);
        btnThemKhach.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThemKhach);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120, 40));
        btnThemKhach.setPreferredSize(btnChon.getPreferredSize());

        loadDataLenTable();
    }

    private void addEvents() {
        txtTuKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTable(txtTuKhoa.getText());
            }
        });

        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonKhachHang();
            }
        });

        btnThemKhach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhach();
            }
        });
    }

    private void xuLyChonKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if (row > -1) {
            String ID = tblKhachHang.getValueAt(row, 0) + "";
            String FirstName = tblKhachHang.getValueAt(row, 1) + "";
            String LastName = tblKhachHang.getValueAt(row, 2) + "";
            String Gender = tblKhachHang.getValueAt(row, 3) + "";
            String PhoneNumber = tblKhachHang.getValueAt(row, 4) + "";
            String Address = tblKhachHang.getValueAt(row, 5) + "";
            double tongChiTieu = Double.parseDouble(tblKhachHang.getValueAt(row, 6) + "");
            String customerTypeID = tblKhachHang.getValueAt(row, 7) + "";

            khachHangTimDuoc = new Customer(ID, FirstName, LastName, Gender, PhoneNumber, Address, tongChiTieu, customerTypeID, true);
        }
        this.dispose();
    }

    private void xuLyThemKhach() {
        DlgThemKhachHang dlg = new DlgThemKhachHang();
        dlg.setVisible(true);
        if (dlg.checkThemKhach) {
            khachHangBUS.readCustomerList();
            loadDataLenTable();
        }
    }

    private void loadDataLenTable() {
        dtmKhachHang.setRowCount(0);
        ArrayList<Customer> dskh = khachHangBUS.getCustomerList();
        if (dskh != null) {
            for (Customer kh : dskh) {
                Vector vec = new Vector();
                vec.add(kh.getCustomerID());
                vec.add(kh.getFirstName());
                vec.add(kh.getLastName());
                vec.add(kh.getGender());
                vec.add(kh.getPhoneNumber());
                vec.add(kh.getAddress());
                vec.add(kh.getTotalSpending());
                vec.add(kh.getCustomerTypeID());
                dtmKhachHang.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        dtmKhachHang.setRowCount(0);
//        ArrayList<Customer> dskh = khachHangBUS.findCustomerByKeyWord(tuKhoa);
//        for (Customer kh : dskh) {
//            Vector vec = new Vector();
//                vec.add(kh.getCustomerID());
//                vec.add(kh.getFirstName());
//                vec.add(kh.getLastName());
//                vec.add(kh.getGender());
//                vec.add(kh.getPhoneNumber());
//                vec.add(kh.getAddress());
//                vec.add(kh.getTotalSpending());
//                vec.add(kh.getCustomerTypeID());
//                dtmKhachHang.addRow(vec);
//        }
        Customer customer = khachHangBUS.findCustomerByID(tuKhoa);
        Vector vec = new Vector();
        vec.add(customer.getCustomerID());
        vec.add(customer.getFirstName());
        vec.add(customer.getLastName());
        vec.add(customer.getGender());
        vec.add(customer.getPhoneNumber());
        vec.add(customer.getAddress());
        vec.add(customer.getTotalSpending());
        vec.add(customer.getCustomerTypeID());
        dtmKhachHang.addRow(vec);

    }

}
