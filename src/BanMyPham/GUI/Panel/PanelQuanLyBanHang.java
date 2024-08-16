package BanMyPham.GUI.Panel;

import BanMyPham.BUS.Order_DetailsBUS;
import BanMyPham.BUS.OrdersBUS;
import BanMyPham.BUS.ProductBUS;
import BanMyPham.BUS.Product_CategoryBUS;
import BanMyPham.DTO.Order_Details;
import BanMyPham.DTO.Orders;
import BanMyPham.DTO.Product;
import BanMyPham.DTO.Product_Category;
import BanMyPham.DTO.Users;
import Main.Main;
import static Main.Main.changLNF;
import MyCustom.MyDialog;
import MyCustom.MyHeaderRenderer;
import MyCustom.MyTable;
import MyCustom.NumericDocumentFilter;
import MyCustom.PanelGradient;
import MyCustom.TransparentPanel;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.sun.net.httpserver.Headers;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.NumberFormatter;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author acer
 */
public class PanelQuanLyBanHang extends javax.swing.JPanel {

    DecimalFormat dcf = new DecimalFormat("###,###");

    private ProductBUS productBUS = new ProductBUS();
//    private NhanVienBUS nvBUS = new NhanVienBUS();
    private Product_CategoryBUS categoryBUS = new Product_CategoryBUS();
    private OrdersBUS ordersBUS = new OrdersBUS();

    JLabel lblTabbedBanHang, lblTabbedOrders;
    final ImageIcon tabbedSelected = new ImageIcon("image/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/ManagerUI/tabbed-btn.png");
    final Color colorPanel = new Color(247, 247, 247);
    CardLayout cardBanHangGroup = new CardLayout();
    JPanel pnCardTabBanHang;
    MyTable tblBanHang, tblCart;
    DefaultTableModel dtmProduct, dtmCart;
    JTextField txtProductID, txtProductName, txtProductPrice;
    JSpinner spnSoLuongBanHang;
    JComboBox<String> cmbProductCategory, cmbNhanVienBan;
    JLabel lblProductImage;
    PanelGradient btnAddToCart, btnDeletePrdInCart, btnExportOrders;

    JTextField txtOrderID, txtNgayLap, txtCustomerID, txtUserID, txtTongTien, txtGhiChu, txtOrderDetailID, txtMaSPCT, txtSoLuongCT, txtDonGiaCT, txtThanhTienCT;
    JTextField txtMinSearch, txtMaxSearch, txtMinNgayLap, txtMaxNgayLap;
    JList<String> listOrders;
    MyTable tblOrdersDetail;
    DefaultTableModel dtmOrdersDetail;
    JButton btnReset, btnResetOrderDetails, btnResetOrders;

    Users user;

    public PanelQuanLyBanHang(Users user) {
        changLNF("Windows");
        initComponents();
        this.user = user;
        addControlsBanHang();
        addEventsBanHang();
    }

    private void addControlsBanHang() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 750;

        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new TransparentPanel();
        //<editor-fold defaultstate="collapsed" desc="Panel Tab Bán hàng & Hoá đơn">
        Font font = new Font("sansserif", Font.PLAIN, 13);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, pnTop.getBackground()));

        lblTabbedBanHang = new JLabel("Bán hàng");
        lblTabbedBanHang.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedBanHang.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedBanHang.setIcon(tabbedSelected);
        lblTabbedBanHang.setBounds(2, 2, 140, 37);
        lblTabbedBanHang.setFont(font);
        lblTabbedBanHang.setForeground(Color.white);
        lblTabbedBanHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedOrders = new JLabel("Hoá đơn");
        lblTabbedOrders.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedOrders.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedOrders.setIcon(tabbedDefault);
        lblTabbedOrders.setBounds(143, 2, 140, 37);
        lblTabbedOrders.setFont(font);
        lblTabbedOrders.setForeground(Color.white);
        lblTabbedOrders.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedBanHang);
        pnTop.add(lblTabbedOrders);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);
        /*
        =========================================================================
                                    PANEL CT BÁN HÀNG
        =========================================================================
         */
        //====================Bảng hàng hoá====================
        //<editor-fold defaultstate="collapsed" desc="Bảng sản phẩm">
        JPanel pnTableBanHang = new TransparentPanel();
        pnTableBanHang.setLayout(new BorderLayout());

        JPanel pnTitleBanHang = new TransparentPanel();
        JLabel lblTitleBanHang = new JLabel("Danh sách sản phẩm");
        lblTitleBanHang.setFont(new Font("Arial", Font.BOLD, 28));
//        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
//        btnReset.setFocusPainted(false);
//        btnReset.setPreferredSize(new Dimension(40, 40));
//        pnTitleBanHang.add(lblTitleBanHang);
//        pnTitleBanHang.add(btnReset);
        pnTableBanHang.add(pnTitleBanHang, BorderLayout.NORTH);

        dtmProduct = new DefaultTableModel();
        dtmProduct.addColumn("Mã SP");
        dtmProduct.addColumn("Tên SP");
        dtmProduct.addColumn("Đơn giá");
        dtmProduct.addColumn("Còn lại");
        dtmProduct.addColumn("Đơn vị tính");
        dtmProduct.addColumn("Ảnh");
        tblBanHang = new MyTable(dtmProduct);

        tblBanHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblBanHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblBanHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblBanHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TableColumnModel columnModelBanHang = tblBanHang.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(77);
        columnModelBanHang.getColumn(1).setPreferredWidth(282);
        columnModelBanHang.getColumn(2).setPreferredWidth(82);
        columnModelBanHang.getColumn(3).setPreferredWidth(85);
        columnModelBanHang.getColumn(4).setPreferredWidth(138);
        columnModelBanHang.getColumn(5).setPreferredWidth(0);

        JScrollPane scrTblBanHang = new JScrollPane(tblBanHang);
        //</editor-fold>
        pnTableBanHang.add(scrTblBanHang, BorderLayout.CENTER);

        //====================Thông tin giỏ hàng====================
        JPanel pnTableGioHang = new TransparentPanel();
        //<editor-fold defaultstate="collapsed" desc="Bảng giỏ hàng">
        pnTableGioHang.setLayout(new BorderLayout());

        JPanel pnTitleGioHang = new TransparentPanel();
        JLabel lblTitleGioHang = new JLabel("Giỏ hàng");
        lblTitleGioHang.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitleGioHang.add(lblTitleGioHang);
        pnTitleGioHang.add(btnReset);
        pnTableGioHang.add(pnTitleGioHang, BorderLayout.NORTH);

        dtmCart = new DefaultTableModel();
        dtmCart.addColumn("Mã SP");
        dtmCart.addColumn("Tên SP");
        dtmCart.addColumn("Số lượng");
        dtmCart.addColumn("Đơn giá");
        dtmCart.addColumn("Thành tiền");

        tblCart = new MyTable(dtmCart);

        tblCart.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblCart.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblCart.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblCart.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TableColumnModel columnModelGioHang = tblCart.getColumnModel();
        columnModelGioHang.getColumn(0).setPreferredWidth(81);
        columnModelGioHang.getColumn(1).setPreferredWidth(279);
        columnModelGioHang.getColumn(2).setPreferredWidth(111);
        columnModelGioHang.getColumn(3).setPreferredWidth(101);
        columnModelGioHang.getColumn(4).setPreferredWidth(100);

        JScrollPane scrTblGioHang = new JScrollPane(tblCart);
        //</editor-fold>
        pnTableGioHang.add(scrTblGioHang, BorderLayout.CENTER);

        //====================Thông tin bán hàng====================
        JPanel pnThongTinBanHang = new TransparentPanel(new MigLayout("wrap,alignx right"));
        //<editor-fold defaultstate="collapsed" desc="Thông tin bán hàng (textfield, button thêm)">
//        pnThongTinBanHang.setLayout(new BoxLayout(pnThongTinBanHang, BoxLayout.Y_AXIS));

        JPanel pnTitleThongTin = new TransparentPanel(new MigLayout("fill"));
        JLabel lblTitleThongTin = new JLabel("Tìm Kiếm Sản Phẩm", JLabel.LEFT);
        lblTitleThongTin.setFont(new Font("Arial", Font.BOLD, 28));
        pnTitleThongTin.add(lblTitleThongTin);
        pnThongTinBanHang.add(pnTitleThongTin, "alignx center");

        JPanel pnLoaiSP = new TransparentPanel(new MigLayout("fill"));
//        pnLoaiSP.setLayout(new BoxLayout(pnLoaiSP, BoxLayout.X_AXIS));
        JLabel lblLoai = new JLabel("Loại SP");
        lblLoai.setFont(font);
        cmbProductCategory = new JComboBox<>();
        cmbProductCategory.setFont(font);
//        cmbProductCategory.setAlignmentX(Component.RIGHT_ALIGNMENT);
        loadDataComboboxProductCategory();
//        pnLoaiSP.add(Box.createHorizontalStrut(20));
        pnLoaiSP.add(lblLoai, "alignx left, gapright 50");
//        pnLoaiSP.add(Box.createHorizontalStrut(20));
        pnLoaiSP.add(cmbProductCategory, "alignx right");
        pnThongTinBanHang.add(pnLoaiSP);

        JPanel pnMaSP = new TransparentPanel(new MigLayout("fill"));
        JLabel lblMa = new JLabel("Mã Sản Phẩm");
        lblMa.setFont(font);
        txtProductID = new JTextField(15);
        txtProductID.setFont(font);
        pnMaSP.add(lblMa, "alignx left, gapright 17");
        pnMaSP.add(txtProductID, "alignx right");
        pnThongTinBanHang.add(pnMaSP);

        JPanel pnTenSP = new TransparentPanel(new MigLayout("fill"));
        JLabel lblTen = new JLabel("Tên Sản Phẩm");
        lblTen.setFont(font);
        txtProductName = new JTextField(15);
        txtProductName.setFont(font);
        txtProductName.setEditable(false);
        pnTenSP.add(lblTen, "alignx left, gapright 15");
        pnTenSP.add(txtProductName, "alignx right");
        pnThongTinBanHang.add(pnTenSP);

        JPanel pnDonGiaSP = new TransparentPanel(new MigLayout("fill"));
        JLabel lblDonGia = new JLabel("Đơn giá");
        lblDonGia.setFont(font);
        txtProductPrice = new JTextField(15);
        txtProductPrice.setFont(font);
        pnDonGiaSP.add(lblDonGia, "alignx left, gapright 51");
        pnDonGiaSP.add(txtProductPrice, "alignx right");
        pnThongTinBanHang.add(pnDonGiaSP);

        JPanel pnSoLuongSP = new TransparentPanel(new MigLayout("fill"));
        JLabel lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setFont(font);
        spnSoLuongBanHang = new JSpinner();
        spnSoLuongBanHang.setFont(font);
        SpinnerNumberModel modeSpinner = new SpinnerNumberModel(1, 1, 100, 1);
        spnSoLuongBanHang.setModel(modeSpinner);
        JFormattedTextField txtSpinner = ((JSpinner.NumberEditor) spnSoLuongBanHang.getEditor()).getTextField();
        ((NumberFormatter) txtSpinner.getFormatter()).setAllowsInvalid(false);
        txtSpinner.setEditable(false);
        txtSpinner.setHorizontalAlignment(JTextField.LEFT);

        pnSoLuongSP.add(lblSoLuong, "alignx left, gapright 45");
        pnSoLuongSP.add(spnSoLuongBanHang, "alignx right");
        pnThongTinBanHang.add(pnSoLuongSP);

//        JPanel pnNhanVienBan = new TransparentPanel();
//        JLabel lblNhanVien = new JLabel("Nhân Viên");
//        lblNhanVien.setFont(font);
//        lblLoai.setFont(font);
//        cmbNhanVienBan = new JComboBox<>();
//        cmbNhanVienBan.setFont(font);
//        loadDataComboboxNhanVienBan();
//        pnNhanVienBan.add(lblNhanVien);
//        pnNhanVienBan.add(cmbNhanVienBan);
//        pnThongTinBanHang.add(pnNhanVienBan);
        JPanel pnButtonBan = new TransparentPanel(new MigLayout("fill"));
        btnAddToCart = new PanelGradient();
        btnAddToCart.button("Thêm vào giỏ");
        btnAddToCart.setBackground(new Color(102, 255, 102));
        btnAddToCart.setColorGradient(new Color(102, 255, 102));

        pnButtonBan.add(btnAddToCart, "alignx center");
        pnThongTinBanHang.add(pnButtonBan, "alignx center,gapleft 20");

        cmbProductCategory.setPreferredSize(txtProductID.getPreferredSize());
//        Dimension sizeLabel = lblNhanVien.getPreferredSize();
//        lblLoai.setPreferredSize(sizeLabel);
//        lblMa.setPreferredSize(sizeLabel);
//        lblTen.setPreferredSize(sizeLabel);
//        lblDonGia.setPreferredSize(sizeLabel);
//        lblSoLuong.setPreferredSize(sizeLabel);
        spnSoLuongBanHang.setPreferredSize(txtProductID.getPreferredSize());
//        cmbNhanVienBan.setPreferredSize(txtMaSPBanHang.getPreferredSize());

//        txtProductID.setEditable(false);
        txtProductPrice.setEditable(false);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Ảnh hàng">
        JPanel pnAnhSanPham = new TransparentPanel();
        pnAnhSanPham.setPreferredSize(new Dimension((int) pnThongTinBanHang.getPreferredSize().getWidth(), 220));
        lblProductImage = new JLabel();
        lblProductImage.setBorder(BorderFactory.createLineBorder(Color.gray));
        lblProductImage.setPreferredSize(new Dimension(200, 200));
        pnAnhSanPham.add(lblProductImage);

        JPanel pnButtonBanHang = new TransparentPanel();
        btnDeletePrdInCart = new PanelGradient();
        btnDeletePrdInCart.button("Xóa");
        btnDeletePrdInCart.setFont(font);
        btnExportOrders = new PanelGradient();
        btnExportOrders.button("Xuất hoá đơn");
        btnExportOrders.setFont(font);
        pnButtonBanHang.setPreferredSize(new Dimension((int) pnThongTinBanHang.getPreferredSize().getWidth(), 50));

        //<editor-fold defaultstate="collapsed" desc="Action cho button">
        ArrayList<PanelGradient> btnSPList = new ArrayList();
        btnSPList.add(btnAddToCart);
        btnSPList.add(btnDeletePrdInCart);
        btnSPList.add(btnExportOrders);
        for (PanelGradient button : btnSPList) {
            button.setBackground(new Color(102, 255, 102));
            button.setColorGradient(new Color(0, 204, 153));
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (button != btnAddToCart) {
                JPanel pnTemp = new TransparentPanel();
                pnTemp.add(button);
                pnButtonBanHang.add(pnTemp);
            }
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(0, 255, 255));
                    button.setColorGradient(new Color(51, 255, 204));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(102, 255, 102));
                    button.setColorGradient(new Color(0, 204, 153));
                }
            });
        }
        //</editor-fold>

        //</editor-fold>
        //=======================================================
        JPanel pnCenter = new TransparentPanel();

        JPanel pnLeftBanHang = new TransparentPanel();
        pnLeftBanHang.setLayout(new BoxLayout(pnLeftBanHang, BoxLayout.Y_AXIS));
        pnLeftBanHang.setPreferredSize(new Dimension(618, h - 41));
        pnTableBanHang.setPreferredSize(new Dimension(618, (h - 41) / 2));
//        pnLeftBanHang.add(pnTableBanHang);
        pnLeftBanHang.add(pnTableGioHang);
        pnCenter.add(pnLeftBanHang);

        JPanel pnRightBanHang = new TransparentPanel();
        pnRightBanHang.setLayout(new BoxLayout(pnRightBanHang, BoxLayout.Y_AXIS));

        pnRightBanHang.add(pnThongTinBanHang);
        pnThongTinBanHang.setPreferredSize(new Dimension((int) pnRightBanHang.getPreferredSize().getWidth(),
                (int) pnTableBanHang.getPreferredSize().getHeight()));

        pnRightBanHang.add(pnAnhSanPham);
        pnRightBanHang.add(pnButtonBanHang);
        pnCenter.add(pnRightBanHang);

        pnCardTabBanHang = new JPanel(cardBanHangGroup);
        pnCardTabBanHang.setPreferredSize(new Dimension(w, (int) (h - pnTop.getPreferredSize().getHeight())));
        JPanel pnCTBanHang = new TransparentPanel();
        pnCTBanHang.setLayout(new BorderLayout());
        pnCTBanHang.add(pnCenter, BorderLayout.CENTER);
        pnCardTabBanHang.add(pnCTBanHang, "1");

        /*
        =========================================================================
                                    PANEL CT HOÁ ĐƠN
        =========================================================================
         */
        JPanel pnCTHoaDon = new JPanel();
        pnCTHoaDon.setLayout(new BorderLayout());

        JPanel pnCTHoaDonLeft = new TransparentPanel();
        pnCTHoaDonLeft.setPreferredSize(new Dimension(420,
                (int) pnCardTabBanHang.getPreferredSize().getHeight()));
        pnCTHoaDonLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY));
        pnCTHoaDonLeft.setLayout(new BoxLayout(pnCTHoaDonLeft, BoxLayout.Y_AXIS));
        pnCTHoaDon.add(pnCTHoaDonLeft, BorderLayout.WEST);

        JLabel lblMaHD, lblNgayLap, lblMaKH, lblMaNV, lblTongTien, lblGhiChu, lblMinsearch, lblMaxSearch, lblMinNgay, lblMaxNgay;
        lblMaHD = new JLabel("Mã HD");
        lblMaKH = new JLabel("Mã KH");
        lblMaNV = new JLabel("NV lập");
        lblNgayLap = new JLabel("Ngày lập");
        lblTongTien = new JLabel("Tổng tiền");
        lblGhiChu = new JLabel("Ghi chú");
        lblMinsearch = new JLabel("Giá từ:");
        lblMaxSearch = new JLabel("đến:");
        lblMinNgay = new JLabel("Ngày lập từ:");
        lblMaxNgay = new JLabel("đến:");

        txtOrderID = new JTextField(10);
        txtCustomerID = new JTextField(10);
        txtUserID = new JTextField(10);
        txtNgayLap = new JTextField(10);
        txtTongTien = new JTextField(10);
        txtGhiChu = new JTextField(10);
        txtMinSearch = new JTextField(7);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào txtMinSearch textField
        ((AbstractDocument) txtMinSearch.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        txtMaxSearch = new JTextField(7);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào txtMaxSearch textField
        ((AbstractDocument) txtMaxSearch.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        txtMinNgayLap = new JTextField(7);
        txtMaxNgayLap = new JTextField(7);

        JPanel pnTitleHoaDon = new TransparentPanel(new FlowLayout());
        JLabel lblTitleHoaDon = new JLabel("THÔNG TIN ĐƠN HÀNG");
        lblTitleHoaDon.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnResetOrders = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnResetOrders.setPreferredSize(new Dimension(40, 40));
        pnTitleHoaDon.add(lblTitleHoaDon);
        pnTitleHoaDon.add(btnResetOrders);
        pnCTHoaDonLeft.add(pnTitleHoaDon);

        JPanel pnMaHD = new TransparentPanel(new FlowLayout());
        pnMaHD.add(lblMaHD);
        lblMaHD.setFont(font);
        txtOrderID.setFont(font);
        pnMaHD.add(txtOrderID);
        pnCTHoaDonLeft.add(pnMaHD);

        JPanel pnMaKH = new TransparentPanel(new FlowLayout());
        pnMaKH.add(lblMaKH);
        lblMaKH.setFont(font);
        txtCustomerID.setFont(font);
        pnMaKH.add(txtCustomerID);
        pnCTHoaDonLeft.add(pnMaKH);

        JPanel pnMaNV = new TransparentPanel(new FlowLayout());
        pnMaNV.add(lblMaNV);
        lblMaNV.setFont(font);
        txtUserID.setFont(font);
        pnMaNV.add(txtUserID);
        pnCTHoaDonLeft.add(pnMaNV);

        JPanel pnNgayLap = new TransparentPanel(new FlowLayout());
        pnNgayLap.add(lblNgayLap);
        lblNgayLap.setFont(font);
        txtNgayLap.setFont(font);
        pnNgayLap.add(txtNgayLap);
        pnCTHoaDonLeft.add(pnNgayLap);

        JPanel pnTongTien = new TransparentPanel(new FlowLayout());
        pnTongTien.add(lblTongTien);
        lblTongTien.setFont(font);
        txtTongTien.setFont(font);
        pnTongTien.add(txtTongTien);
        pnCTHoaDonLeft.add(pnTongTien);

        JPanel pnGhiChu = new TransparentPanel(new FlowLayout());
        pnGhiChu.add(lblGhiChu);
        lblGhiChu.setFont(font);
        txtGhiChu.setFont(font);
        pnGhiChu.add(txtGhiChu);
        pnCTHoaDonLeft.add(pnGhiChu);

        JPanel pnSearchPrice = new TransparentPanel(new FlowLayout());
        lblMinsearch.setFont(font);
        lblMaxSearch.setFont(font);
        txtMinSearch.setFont(font);
        txtMaxSearch.setFont(font);
        pnSearchPrice.add(lblMinsearch);
        pnSearchPrice.add(txtMinSearch);
        pnSearchPrice.add(lblMaxSearch);
        pnSearchPrice.add(txtMaxSearch);
        pnCTHoaDonLeft.add(pnSearchPrice);

        JPanel pnSearchDate = new TransparentPanel(new FlowLayout());
        lblMinNgay.setFont(font);
        lblMaxNgay.setFont(font);
        txtMinNgayLap.setFont(font);
        txtMaxNgayLap.setFont(font);
        pnSearchDate.add(lblMinNgay);
        pnSearchDate.add(txtMinNgayLap);
        pnSearchDate.add(lblMaxNgay);
        pnSearchDate.add(txtMaxNgayLap);
        pnCTHoaDonLeft.add(pnSearchDate);

        Dimension lblHoaDonSize = lblTongTien.getPreferredSize();
        lblMaHD.setPreferredSize(lblHoaDonSize);
        lblNgayLap.setPreferredSize(lblHoaDonSize);
        lblMaKH.setPreferredSize(lblHoaDonSize);
        lblMaNV.setPreferredSize(lblHoaDonSize);
        lblTongTien.setPreferredSize(lblHoaDonSize);
        lblGhiChu.setPreferredSize(lblHoaDonSize);
        lblMinsearch.setPreferredSize(lblMinNgay.getPreferredSize());

        txtOrderID.setEditable(false);
        txtCustomerID.setEditable(false);
        txtUserID.setEditable(false);
        txtNgayLap.setEditable(false);
        txtTongTien.setEditable(false);
        txtGhiChu.setEditable(false);

        JPanel pnListHoaDon = new TransparentPanel();
        listOrders = new JList<>();
        listOrders.setFont(font);
        listOrders.setPreferredSize(new Dimension(
                (int) pnCTHoaDonLeft.getPreferredSize().getWidth() - 22,
                400));
        loadDataListOrders();
        JScrollPane scrHoaDon = new JScrollPane(listOrders,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrHoaDon.setPreferredSize(listOrders.getPreferredSize());
        pnListHoaDon.add(scrHoaDon);
        pnCTHoaDonLeft.add(pnListHoaDon);

        //=======================================================
        JPanel pnCTHoaDonRight = new TransparentPanel();
        pnCTHoaDonRight.setLayout(new BorderLayout());

        JPanel pnTopCTHoaDonRight = new TransparentPanel();
        pnTopCTHoaDonRight.setLayout(new BoxLayout(pnTopCTHoaDonRight, BoxLayout.Y_AXIS));
        JLabel lblMaHDCT, lblMaSPCT, lblSoLuongCT, lblDonGiaCT, lblThanhTienCT;
        lblMaHDCT = new JLabel("Mã HD");
        lblMaSPCT = new JLabel("Sản phẩm");
        lblSoLuongCT = new JLabel("Số lượng");
        lblDonGiaCT = new JLabel("Đơn giá");
        lblThanhTienCT = new JLabel("Thành tiền");

        txtOrderDetailID = new JTextField(20);
        txtMaSPCT = new JTextField(20);
        txtSoLuongCT = new JTextField(20);
        txtDonGiaCT = new JTextField(20);
        txtThanhTienCT = new JTextField(20);

        JLabel lblTitleCTHD = new JLabel("CHI TIẾT HOÁ ĐƠN");
        JPanel pnTitleCT = new TransparentPanel();
        lblTitleCTHD.setFont(new Font("Tahoma", Font.BOLD, 28));

        btnResetOrderDetails = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnResetOrderDetails.setPreferredSize(new Dimension(40, 40));
        pnTitleCT.add(lblTitleCTHD);
        pnTitleCT.add(btnResetOrderDetails);
        pnTopCTHoaDonRight.add(pnTitleCT);

        JPanel pnMaHDCT = new TransparentPanel();
        lblMaHDCT.setFont(font);
        txtOrderDetailID.setFont(font);
        pnMaHDCT.add(lblMaHDCT);
        pnMaHDCT.add(txtOrderDetailID);
        pnTopCTHoaDonRight.add(pnMaHDCT);

        JPanel pnMaSPCT = new TransparentPanel();
        lblMaSPCT.setFont(font);
        txtMaSPCT.setFont(font);
        pnMaSPCT.add(lblMaSPCT);
        pnMaSPCT.add(txtMaSPCT);
        pnTopCTHoaDonRight.add(pnMaSPCT);

        JPanel pnSoLuongCT = new TransparentPanel();
        lblSoLuongCT.setFont(font);
        txtSoLuongCT.setFont(font);
        pnSoLuongCT.add(lblSoLuongCT);
        pnSoLuongCT.add(txtSoLuongCT);
        pnTopCTHoaDonRight.add(pnSoLuongCT);

        JPanel pnDonGiaCT = new TransparentPanel();
        lblDonGiaCT.setFont(font);
        txtDonGiaCT.setFont(font);
        pnDonGiaCT.add(lblDonGiaCT);
        pnDonGiaCT.add(txtDonGiaCT);
        pnTopCTHoaDonRight.add(pnDonGiaCT);

        JPanel pnThanhTienCT = new TransparentPanel();
        lblThanhTienCT.setFont(font);
        txtThanhTienCT.setFont(font);
        pnThanhTienCT.add(lblThanhTienCT);
        pnThanhTienCT.add(txtThanhTienCT);
        pnTopCTHoaDonRight.add(pnThanhTienCT);

        Dimension lblCTHDSize = lblThanhTienCT.getPreferredSize();
        lblMaHDCT.setPreferredSize(lblCTHDSize);
        lblMaSPCT.setPreferredSize(lblCTHDSize);
        lblSoLuongCT.setPreferredSize(lblCTHDSize);
        lblDonGiaCT.setPreferredSize(lblCTHDSize);
        lblThanhTienCT.setPreferredSize(lblCTHDSize);
        txtOrderDetailID.setEditable(false);
        txtMaSPCT.setEditable(false);
        txtSoLuongCT.setEditable(false);
        txtDonGiaCT.setEditable(false);
        txtThanhTienCT.setEditable(false);

        pnCTHoaDonRight.add(pnTopCTHoaDonRight, BorderLayout.NORTH);

        dtmOrdersDetail = new DefaultTableModel();
        dtmOrdersDetail.addColumn("Mã Đơn Hàng");
        dtmOrdersDetail.addColumn("Mã Sản Phẩm");
        dtmOrdersDetail.addColumn("Số lượng");
        dtmOrdersDetail.addColumn("Đơn giá");
        dtmOrdersDetail.addColumn("Thành tiền");
        tblOrdersDetail = new MyTable(dtmOrdersDetail);
        JScrollPane scrCTHoaDon = new JScrollPane(tblOrdersDetail);
        pnCTHoaDonRight.add(scrCTHoaDon, BorderLayout.CENTER);
        loadDataTblOrderDetails();

        pnCTHoaDon.add(pnCTHoaDonRight, BorderLayout.CENTER);

        //==========
        pnCardTabBanHang.add(pnCTHoaDon, "2");

        //=======================================================
        this.add(pnCardTabBanHang);
        loadDataTableProducts();
        txtProductName.requestFocus();
        lblProductImage.setIcon(getProductImage(""));

//        if (cmbNhanVienBan != null) {
//            cmbNhanVienBan.setEnabled(false); // hoặc true, tùy vào yêu cầu của bạn
//        }
    }

    private void addEventsBanHang() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleResetData();
            }
        });

        txtProductID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindProductByID();
            }
        });

        lblTabbedBanHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedOrders.setIcon(tabbedDefault);
                lblTabbedBanHang.setIcon(tabbedSelected);
                cardBanHangGroup.show(pnCardTabBanHang, "1");
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

        lblTabbedOrders.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedOrders.setIcon(tabbedSelected);
                lblTabbedBanHang.setIcon(tabbedDefault);
                cardBanHangGroup.show(pnCardTabBanHang, "2");
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

        tblBanHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClickTblBanHang();
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

        tblCart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClickTblCart();
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

        btnAddToCart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleAddToCart();
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

        btnDeletePrdInCart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleDelProdInCart();
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

        btnExportOrders.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleExportOrder();
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

        cmbProductCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTableProducts();
            }
        });

        txtProductName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindByName();
            }
        });

        listOrders.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleDisplayOrderDetails();
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

        tblOrdersDetail.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClickTblOrderDetails();
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

        btnResetOrderDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTblOrderDetails();
            }
        });

        btnResetOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataListOrders();
                loadDataTblOrderDetails();
            }
        });

        txtMinSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaxSearch.requestFocus();
            }
        });

        txtMaxSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindToPrice();
            }
        });

        txtMinNgayLap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaxNgayLap.requestFocus();
            }
        });

        txtMaxNgayLap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindToDate();
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

    private void loadDataComboboxProductCategory() {
        cmbProductCategory.removeAllItems();
        cmbProductCategory.addItem("0 - Chọn loại");
        ArrayList<Product_Category> categoryList = categoryBUS.getCategoryList();

        for (Product_Category category : categoryList) {
            cmbProductCategory.addItem(category.getProductCategoryID() + " - " + category.getProductCategoryName());
        }
    }

    private void loadDataTableProducts() {
        dtmProduct.setRowCount(0);
        ArrayList<Product> productList = null;

        if (cmbProductCategory.getItemCount() > 0) {
            String loai = cmbProductCategory.getSelectedItem() + "";
            String loaiArr[] = loai.split("-");
            String loaiSP = loaiArr[0].trim();

            if (loaiSP.equals("0")) {
                productList = productBUS.getProductsList();
            } else {
                productList = productBUS.getProductByCategoryID(loaiSP);
            }
        } else {
            productList = productBUS.getProductsList();
        }

        for (Product product : productList) {
            Vector vec = new Vector();
            vec.add(product.getProductID());
            vec.add(product.getProductName());
            vec.add(dcf.format(product.getPrice()));
            vec.add(dcf.format(product.getQuantity()));
            vec.add(product.getUnits());
            vec.add(product.getImage());
            dtmProduct.addRow(vec);
        }
    }

    private void loadDataListOrders() {
        ArrayList<Orders> lstOrds = ordersBUS.getListOrders();
        addDataListOrders(lstOrds);
    }

    private void addDataListOrders(ArrayList<Orders> lstOrds) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (lstOrds != null) {
            for (Orders order : lstOrds) {
                listModel.addElement(order.getOrderID() + " | " + order.getOrderDate() + " === " + dcf.format(order.getTotalCost()) + " VND");
            }
            listOrders.setModel(listModel);
        }
    }

    private void loadImage(String image) {
        lblProductImage.setIcon(getProductImage(image));
    }

    File fileProductImage;

    private ImageIcon getProductImage(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File("image/Product/" + src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/Product/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileProductImage = new File("image/Product/" + src);
        } catch (IOException e) {
            fileProductImage = new File("image/Product/default.png");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            return new ImageIcon(dimg);
        }
        return null;
    }

    private Order_DetailsBUS ordDtlBUS = new Order_DetailsBUS();

    private void loadDataTblOrderDetails() {
        ordDtlBUS.readListOrdDetails();
        ArrayList<Order_Details> orderDetailsList = ordDtlBUS.getOrderDetailsList();
        addDataTableOrderDetails(orderDetailsList);
    }

    private void addDataTableOrderDetails(ArrayList<Order_Details> orderDetailsList) {
        dtmOrdersDetail.setRowCount(0);
        for (Order_Details ordDetail : orderDetailsList) {
            Vector<String> vec = new Vector<>();
            vec.add(ordDetail.getOrderID() + "");
            vec.add(ordDetail.getProductID() + "");
            vec.add(ordDetail.getQuantity() + "");
            Product productTmp = productBUS.getProduct(ordDetail.getProductID());
            vec.add(dcf.format(productTmp.getPrice()));
            double totalCost = productTmp.getPrice() * ordDetail.getQuantity();
            vec.add(dcf.format(totalCost));
            dtmOrdersDetail.addRow(vec);
        }
    }

    private void loadDataTblOrderDetails(String orderID) {
        ArrayList<Order_Details> orderDetailsList = ordDtlBUS.getListOrderDetalsToOrderID(orderID);

        addDataTableOrderDetails(orderDetailsList);
    }

    private void handleClickTblBanHang() {
        int row = tblBanHang.getSelectedRow();
        if (row > -1) {
            String id = tblBanHang.getValueAt(row, 0) + "";
            String name = tblBanHang.getValueAt(row, 1) + "";
            String price = tblBanHang.getValueAt(row, 2) + "";
            String image = tblBanHang.getValueAt(row, 5) + "";
            int quantity = Integer.parseInt(tblBanHang.getValueAt(row, 3) + "");
            if (quantity < 1) {
                MyDialog dlg = new MyDialog("Sản phẩm đã hết hàng", MyDialog.ERROR_DIALOG);
                return;
            }

            SpinnerNumberModel modeSpinner = new SpinnerNumberModel(1, 1, quantity, 1);
            spnSoLuongBanHang.setModel(modeSpinner);
            JFormattedTextField txtSpinner = ((JSpinner.NumberEditor) spnSoLuongBanHang.getEditor()).getTextField();
            ((NumberFormatter) txtSpinner.getFormatter()).setAllowsInvalid(false);
            txtSpinner.setEditable(false);
            txtSpinner.setHorizontalAlignment(JTextField.LEFT);

            txtProductID.setText(id);
            txtProductName.setText(name);
            txtProductPrice.setText(price);
            loadImage(image);
        }
    }

    private void handleClickTblOrderDetails() {
        int row = tblOrdersDetail.getSelectedRow();
        if (row > -1) {
            String orderID = tblOrdersDetail.getValueAt(row, 0) + "";
            String productID = productBUS.getProduct("" + tblOrdersDetail.getValueAt(row, 1)).getProductName();
            String quantity = tblOrdersDetail.getValueAt(row, 2) + "";

            String price = tblOrdersDetail.getValueAt(row, 3) + "";
            String totalCost = tblOrdersDetail.getValueAt(row, 4) + "";
            txtOrderDetailID.setText(orderID);
            txtMaSPCT.setText(productID);
            txtSoLuongCT.setText(quantity);
            txtDonGiaCT.setText(price);
            txtThanhTienCT.setText(totalCost);
        }
    }

    private void handleClickTblCart() {
        int row = tblCart.getSelectedRow();
        if (row < 0) {
            return;
        }
        String id = tblCart.getValueAt(row, 0) + "";
        txtProductID.setText(id);
        handleFindProductByID();
//        loadImage(productBUS.getImage(id));
    }

    private void handleDisplayOrderDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String orderString = listOrders.getSelectedValue();
        String[] stOrderID = orderString.split(" | ");

        Orders order = ordersBUS.getOrderSameID(stOrderID[0]);
        txtOrderID.setText(order.getOrderID() + "");
        txtCustomerID.setText(order.getCustomerID() + "");
        txtUserID.setText(order.getUserID() + "");

        txtNgayLap.setText(sdf.format(order.getOrderDate()));
        txtTongTien.setText(dcf.format(order.getTotalCost()));
//        txtGhiChu.setText(order.getGhiChu());

        // Gọi hiển thị data trên tblCTHoaDon
        loadDataTblOrderDetails(stOrderID[0]);
    }

    private void handleResetData() {
        loadDataComboboxProductCategory();
        cmbProductCategory.setSelectedIndex(0);
//        loadDataComboboxNhanVienBan();
        txtProductID.setText("");
        txtProductName.setText("");
        txtProductPrice.setText("");

        SpinnerNumberModel modeSpinner = new SpinnerNumberModel(1, 1, 100, 1);
        spnSoLuongBanHang.setModel(modeSpinner);
        JFormattedTextField txtSpinner = ((JSpinner.NumberEditor) spnSoLuongBanHang.getEditor()).getTextField();
        ((NumberFormatter) txtSpinner.getFormatter()).setAllowsInvalid(false);
        txtSpinner.setEditable(false);
        txtSpinner.setHorizontalAlignment(JTextField.LEFT);
        loadImage("");
    }

    private void handleAddToCart() {
//        int row = tblBanHang.getSelectedRow();
//        if (row < 0) {
//            return;
//        }
        Product product = productBUS.getProduct(txtProductID.getText());
        String id = txtProductID.getText();
        String name = txtProductName.getText();
        String price = txtProductPrice.getText();
        int quantity = Integer.parseInt(spnSoLuongBanHang.getValue() + "");
        int quantityRemain = product.getQuantity();

        if (quantity > quantityRemain || quantityRemain <= 0) {
            new MyDialog("Sản phẩm đã hết hàng", MyDialog.ERROR_DIALOG);
            return;
        }

        txtProductID.setText("");
        txtProductName.setText("");
        txtProductPrice.setText("");
        spnSoLuongBanHang.setValue(0);

        if (id.trim().equalsIgnoreCase("")) {
            return;
        }
        for (int i = 0; i < tblCart.getRowCount(); i++) {
            String tblID = tblCart.getValueAt(i, 0) + "";
            if (tblID.equals(id)) {
                int soLuongAdd = Integer.parseInt(tblCart.getValueAt(i, 2) + "");
                soLuongAdd += quantity;
                price = price.replace(",", "");
                double productPrice = Double.parseDouble(price);

                tblCart.setValueAt(soLuongAdd, i, 2);
                tblCart.setValueAt(dcf.format(quantity * productPrice), i, 4);

                // cập nhật lại số lượng trong db
                productBUS.addQuantityOfProduct(id, -quantity);
                productBUS.readProducstList();
                loadDataTableProducts();
                return;
            }
        }

        Vector vec = new Vector();
        vec.add(id);
        vec.add(name);
        vec.add(quantity);
        vec.add(price);
        price = price.replace(",", "");
        double productPrice = Double.parseDouble(price);
        vec.add(dcf.format(quantity * productPrice));
        // cập nhật lại số lượng trong db
        productBUS.addQuantityOfProduct(id, -quantity);
        productBUS.readProducstList();
        loadDataTableProducts();
        dtmCart.addRow(vec);
    }

    public void handleExit() {
        int row = tblCart.getRowCount();
        if (row > 0) {
            for (int i = 0; i < row; i++) {
                handleDelProdInCart(i);
            }
        }
    }

    private void handleDelProdInCart() {
        int row = tblCart.getSelectedRow();
        if (row > -1) {
            String id = tblCart.getValueAt(row, 0) + "";
            int soLuong = Integer.parseInt(tblCart.getValueAt(row, 2) + "");
            productBUS.addQuantityOfProduct(id, soLuong);
            productBUS.readProducstList();
            loadDataTableProducts();
            handleResetData();
            dtmCart.removeRow(row);
        }
    }

    private void handleDelProdInCart(int row) {
        if (row > -1) {
            String id = tblCart.getValueAt(row, 0) + "";
            int soLuong = Integer.parseInt(tblCart.getValueAt(row, 2) + "");
            productBUS.addQuantityOfProduct(id, soLuong);
            productBUS.readProducstList();
            loadDataTableProducts();
        }
    }

    private void handleFindByName() {
        String name = txtProductName.getText().toLowerCase();
        dtmProduct.setRowCount(0);
        ArrayList<Product> prdList = null;
        prdList = productBUS.getProductByName(name);

        for (Product product : prdList) {
            Vector vec = new Vector();
            vec.add(product.getProductID());
            vec.add(product.getProductName());
            vec.add(dcf.format(product.getPrice()));
            vec.add(dcf.format(product.getQuantity()));
            vec.add(product.getUnits());
            vec.add(product.getImage());
            dtmProduct.addRow(vec);
        }
    }

    private void handleFindToPrice() {
        if (txtMinSearch.getText().equals("") && txtMaxSearch.getText().equals("")) {
            new MyDialog("Hãy nhập khoảng giá cần tìm!", MyDialog.ERROR_DIALOG);
            return;
        }
        ArrayList<Orders> listOrders = ordersBUS.getOrderByCost(Double.parseDouble(txtMinSearch.getText()), Double.parseDouble(txtMaxSearch.getText()));
        addDataListOrders(listOrders);
    }

    private void handleFindProductByID() {
        if (txtProductID.getText().equals("")) {
            new MyDialog("Hãy nhập mã sản phẩm cần tìm!", MyDialog.ERROR_DIALOG);
            return;
        }
        Product product = productBUS.getProduct(txtProductID.getText());
        txtProductName.setText(product.getProductName());
        txtProductPrice.setText("" + product.getPrice());
        int flag = 0;
        for (int i = 0; i < cmbProductCategory.getItemCount(); i++) {
            if (cmbProductCategory.getItemAt(i).contains(product.getProductCategoryID())) {
                flag = i;
                break;
            }
        }
        cmbProductCategory.setSelectedIndex(flag);
        loadImage(product.getImage());

         int quantityMax=product.getQuantity();
        if(product.getQuantity()<1){
            quantityMax=1;
        }
        //Nếu muốn chỉnh ràng buộc thẳng thì bật phần code này, nếu muốn ràng buộc hiện thông báo nếu sản phẩm addToCart vượt quá tồn kho sẽ xuất thông báo hết hàng thì tắt
        SpinnerNumberModel modeSpinner = new SpinnerNumberModel(1, 1, quantityMax, 1);
        spnSoLuongBanHang.setModel(modeSpinner);
        JFormattedTextField txtSpinner = ((JSpinner.NumberEditor) spnSoLuongBanHang.getEditor()).getTextField();
        ((NumberFormatter) txtSpinner.getFormatter()).setAllowsInvalid(false);
        txtSpinner.setEditable(false);
        txtSpinner.setHorizontalAlignment(JTextField.LEFT);
    }

    private void handleFindToDate() {
        ArrayList<Orders> listOrders = ordersBUS.getListHoaDonTheoNgay(txtMinNgayLap.getText(), txtMaxNgayLap.getText());
        addDataListOrders(listOrders);
    }

    private void handleExportOrder() {
        ArrayList<Vector> listCart = new ArrayList<>();
        int row = tblCart.getRowCount();
        if (row == 0) {
            return;
        }
        int tongTien = 0;
        for (int i = 0; i < row; i++) {
            Vector vec = new Vector();
            vec.add(tblCart.getValueAt(i, 0));
            vec.add(tblCart.getValueAt(i, 1));
            vec.add(tblCart.getValueAt(i, 2));
            vec.add(tblCart.getValueAt(i, 3));
            vec.add(tblCart.getValueAt(i, 4));
            tongTien += Integer.parseInt((tblCart.getValueAt(i, 4) + "").replace(",", ""));
            listCart.add(vec);
        }

        XuatHoaDonGUI hoaDonUI = new XuatHoaDonGUI(listCart, tongTien, user);
        hoaDonUI.setVisible(true);
        if (hoaDonUI.checkBanHang) {
            dtmCart.setRowCount(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
