/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package BanMyPham.GUI.Panel;

import BanMyPham.BUS.Product_SaleBUS;
import BanMyPham.DTO.Product_Sale;
import Main.Main;
import static Main.Main.changLNF;
import MyCustom.MyTable;
import MyCustom.NumericDocumentFilter;
import MyCustom.TransparentPanel;
import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.LocalDateTime.now;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;

public class PanelQuanLyKhuyenMai extends javax.swing.JPanel {

    public PanelQuanLyKhuyenMai() {
        changLNF("Windows");
        initComponents();
        addControls();
        addEvents();
    }

    private Product_SaleBUS productSaleBUS = new Product_SaleBUS();
    final Color colorPanel = new Color(247, 247, 247);

    JButton btnReset, btnThem, btnSua;
    JTextField txtMa, txtTen, txtPhanTram, txtDieuKien;
    MyTable tblKhuyenMai;
    DefaultTableModel dtmKhuyenMai;
    JDateChooser dateBD, dateKT;

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 750;

        //=================MAIN PANEL=================
        //=====TITLE====
        JPanel pnMain = new TransparentPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ MÃ KHUYẾN MÃI</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnMain.add(pnTitle);

        //========TEXTFIELD=========
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblMa, lblTen, lblPhanTram, lblDieuKien, lblNgayBD, lblNgayKT;
        lblMa = new JLabel("Mã Khuyến mãi");
        lblTen = new JLabel("Tên chương trình");
        lblPhanTram = new JLabel("Phần trăm giảm");
        lblDieuKien = new JLabel("Điều kiện (>x)");
        lblNgayBD = new JLabel("Ngày bắt đầu");
        lblNgayKT = new JLabel("Ngày kết thúc");

        lblMa.setFont(font);
        lblTen.setFont(font);
        lblPhanTram.setFont(font);
        lblDieuKien.setFont(font);
        lblNgayBD.setFont(font);
        lblNgayKT.setFont(font);

        Date currentDate = new Date();

        txtMa = new JTextField(20);
        txtTen = new JTextField(20);
        txtPhanTram = new JTextField(20);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào txtPhanTram textField
        ((AbstractDocument) txtPhanTram.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        txtDieuKien = new JTextField(20);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào txtDieuKien textField
        ((AbstractDocument) txtDieuKien.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        dateBD = new JDateChooser();
        dateBD.setDateFormatString("dd/MM/yyyy");
        dateBD.setMinSelectableDate(currentDate);
        dateKT = new JDateChooser();
        dateKT.setDateFormatString("dd/MM/yyyy");
        dateKT.setMinSelectableDate(currentDate);

        // Thêm trình lắng nghe sự kiện cho dateBD
        dateBD.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = dateBD.getDate();
                    if (selectedDate != null) {
                        // Khi ngày được chọn trong dateBD thay đổi, cập nhật ngày tối thiểu của dateKT
                        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        dateKT.setMinSelectableDate(java.sql.Date.valueOf(localDate));
                    }
                }
            }
        });

        // Thêm trình lắng nghe sự kiện cho dateKT
        dateKT.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = dateKT.getDate();
                    if (selectedDate != null) {
                        // Khi ngày được chọn trong dateKT thay đổi, cập nhật ngày tối đa của dateBD
                        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        dateBD.setMaxSelectableDate(java.sql.Date.valueOf(localDate));
                    }
                }
            }
        });

        txtMa.setEditable(false);
        dateBD.getCalendarButton().setPreferredSize(new Dimension(32, 32));
        dateBD.getCalendarButton().setIcon(new ImageIcon("image/icons8_calendar_25_20px.png"));
        dateKT.getCalendarButton().setPreferredSize(dateBD.getCalendarButton().getPreferredSize());
        dateKT.getCalendarButton().setIcon(dateBD.getCalendarButton().getIcon());

        txtMa.setFont(font);
        txtTen.setFont(font);
        txtPhanTram.setFont(font);
        txtDieuKien.setFont(font);
        dateBD.setFont(font);
        dateKT.setFont(font);

        JPanel pnMa = new TransparentPanel();
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        JPanel pnTen = new TransparentPanel();
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnPhanTram = new TransparentPanel();
        pnPhanTram.add(lblPhanTram);
        pnPhanTram.add(txtPhanTram);
        pnTextField.add(pnPhanTram);

        JPanel pnDieuKien = new TransparentPanel();
        pnDieuKien.add(lblDieuKien);
        pnDieuKien.add(txtDieuKien);
        pnTextField.add(pnDieuKien);

        JPanel pnNgayBD = new TransparentPanel();
        pnNgayBD.add(lblNgayBD);
        pnNgayBD.add(dateBD);
        pnTextField.add(pnNgayBD);

        JPanel pnNgayKT = new TransparentPanel();
        pnNgayKT.add(lblNgayKT);
        pnNgayKT.add(dateKT);
        pnTextField.add(pnNgayKT);

        pnMain.add(pnTextField);

        Dimension lblSize = lblTen.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblPhanTram.setPreferredSize(lblSize);
        lblDieuKien.setPreferredSize(lblSize);
        lblNgayBD.setPreferredSize(lblSize);
        lblNgayKT.setPreferredSize(lblSize);
        dateBD.setPreferredSize(txtDieuKien.getPreferredSize());
        dateKT.setPreferredSize(txtDieuKien.getPreferredSize());

        //==========BUTTON PANEL===============
        JPanel pnButton = new TransparentPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnThem.setFont(font);
        btnSua.setFont(font);
        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnMain.add(pnButton);
        btnSua.setPreferredSize(btnThem.getPreferredSize());

        //======================TABLE======================
        JPanel pnTable = new TransparentPanel(new BorderLayout());
        dtmKhuyenMai = new DefaultTableModel();
        dtmKhuyenMai.addColumn("Mã KM");
        dtmKhuyenMai.addColumn("Chương trình");
        dtmKhuyenMai.addColumn("Phần trăm KM");
        dtmKhuyenMai.addColumn("Điều kiện");
        dtmKhuyenMai.addColumn("Ngày bắt đầu");
        dtmKhuyenMai.addColumn("Ngày kết thúc");
        dtmKhuyenMai.addColumn("Tình trạng");

        tblKhuyenMai = new MyTable(dtmKhuyenMai);
        JScrollPane scrTblKhuyenMai = new JScrollPane(tblKhuyenMai);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhuyenMai.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tblKhuyenMai.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tblKhuyenMai.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        TableColumnModel columnModelBanHang = tblKhuyenMai.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(24);
        columnModelBanHang.getColumn(1).setPreferredWidth(189);
        columnModelBanHang.getColumn(2).setPreferredWidth(66);
        columnModelBanHang.getColumn(3).setPreferredWidth(56);
        columnModelBanHang.getColumn(4).setPreferredWidth(81);
        columnModelBanHang.getColumn(5).setPreferredWidth(81);
        columnModelBanHang.getColumn(6).setPreferredWidth(92);

        pnTable.add(scrTblKhuyenMai, BorderLayout.CENTER);
        pnMain.add(pnTable);

        this.add(pnMain, BorderLayout.CENTER);

        loadDataTblKhuyenMai();
    }

    private void addEvents() {
        tblKhuyenMai.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblKhuyenMai();
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

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhuyenMai();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhuyenMai();
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtPhanTram.setText("");
                txtDieuKien.setText("");
                dateBD.setDate(null);
                dateKT.setDate(null);
            }
        });
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
    private void loadDataTblKhuyenMai() {
        dtmKhuyenMai.setRowCount(0);
        productSaleBUS.readSaleList();
        ArrayList<Product_Sale> dsg = productSaleBUS.getSaleList();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat(">###,###");
        for (Product_Sale sale : dsg) {
            Vector vec = new Vector();
            vec.add(sale.getSaleID());
            vec.add(sale.getSaleName());
            vec.add(sale.getDiscountPercentage());
            vec.add(dcf.format(sale.getCondition()));
            vec.add(sdf.format(sale.getStartDate()));
            vec.add(sdf.format(sale.getEndDate()));

            // So sánh với ngày hiện tại
            Date now = new Date();
            if (sale.getStartDate().equals(now) || sale.getStartDate().before(now) && sale.getEndDate().after(now)) {
                vec.add("Có hiệu lực");
            } else {
                vec.add("Không hiệu lực");
            }
            dtmKhuyenMai.addRow(vec);
        }
    }

    private void xuLyClickTblKhuyenMai() {
        int row = tblKhuyenMai.getSelectedRow();
        if (row > -1) {
            String ma = tblKhuyenMai.getValueAt(row, 0) + "";
            String ten = tblKhuyenMai.getValueAt(row, 1) + "";
            String phanTram = tblKhuyenMai.getValueAt(row, 2) + "";
            String dieuKien = tblKhuyenMai.getValueAt(row, 3) + "";
            String start = tblKhuyenMai.getValueAt(row, 4) + "";
            String end = tblKhuyenMai.getValueAt(row, 5) + "";

            dieuKien = dieuKien.replace(">", "");
            dieuKien = dieuKien.replace(",", "");
            java.util.Date ngayBD = new Date();
            java.util.Date ngayKT = new Date();
            try {
                ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(start);
                ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            } catch (Exception e) {
            }

            txtMa.setText(ma);
            txtTen.setText(ten);
            txtPhanTram.setText(phanTram);
            txtDieuKien.setText(dieuKien);
            dateBD.setDate(ngayBD);
            dateKT.setDate(ngayKT);
        }
    }

    private void xuLyThemKhuyenMai() {
        boolean flag = productSaleBUS.addSale(txtTen.getText(), txtPhanTram.getText(), txtDieuKien.getText(), dateBD.getDate(), dateKT.getDate());
        if (flag) {
            loadDataTblKhuyenMai();
        }
    }

    private void xuLySuaKhuyenMai() {
        //cần chỉnh lại parameter
        boolean flag = productSaleBUS.editSale(txtMa.getText(), txtTen.getText(), txtPhanTram.getText(), txtDieuKien.getText(), dateBD.getDate(), dateKT.getDate(), false);
        if (flag) {
            loadDataTblKhuyenMai();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
