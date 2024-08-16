package BanMyPham.GUI.Panel;

import BanMyPham.BUS.ProductBUS;
import BanMyPham.BUS.Product_CategoryBUS;
import BanMyPham.DTO.Product;
import BanMyPham.DTO.Product_Category;
import BanMyPham.GUI.Dialog.DlgQuanLyLoai;
import static Main.Main.changLNF;
import MyCustom.MyDialog;
import MyCustom.MyFileChooser;
import MyCustom.MyTable;
import MyCustom.NumericDocumentFilter;
import MyCustom.TransparentPanel;
import MyCustom.XuLyFileExcel;
import java.awt.BorderLayout;
import java.awt.Color;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;

public class PanelQuanLySanPham extends javax.swing.JPanel {

    public PanelQuanLySanPham() {
        changLNF("Windows");
        initComponents();
        addControlsSanPham();
        addEventsSanPham();
    }

    ProductBUS productBUS = new ProductBUS();
    Product_CategoryBUS categoryBUS = new Product_CategoryBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblProduct;
    DefaultTableModel dtmProduct;
    JTextField txtID, txtName, txtQuantity, txtUnits, txtPrice, txtTimKiem;
    JComboBox<String> cmbCategory;
    JButton btnThem, btnSua, btnXoa, btnTim, btnChonAnh, btnReset, btnXuatExcel, btnNhapExcel;
    JLabel lblProductImage;

    private void addControlsSanPham() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 750;

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ SẢN PHẨM</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        this.add(pnTitle);

        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        //================PANEL INPUT=========
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));
        JLabel lblID, lblName, lblCategory, lblQuantity, lblUnits, lblPrice;

        lblID = new JLabel("Mã SP");
        lblName = new JLabel("Tên SP");
        lblCategory = new JLabel("Loại");
        lblQuantity = new JLabel("Số lượng");
        lblUnits = new JLabel("Đơn vị tính");
        lblPrice = new JLabel("Đơn giá");

        txtID = new JTextField(15);
        txtID.setEditable(false);
        txtName = new JTextField(15);
        cmbCategory = new JComboBox<String>();
        txtQuantity = new JTextField(15);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào txtQuantity textField
        ((AbstractDocument) txtQuantity.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        txtUnits = new JTextField(15);
        txtPrice = new JTextField(15);
        //Ràng buộc người dùng không thể nhập hoặc dán chữ vào txtPrice textField
        ((AbstractDocument) txtPrice.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        
        txtQuantity.setEditable(false);
        txtPrice.setEditable(false);

        JPanel pnID = new TransparentPanel();
        lblID.setFont(font);
        txtID.setFont(font);
        pnID.add(lblID);
        pnID.add(txtID);
        pnTextField.add(pnID);

        JPanel pnName = new TransparentPanel();
        lblName.setFont(font);
        txtName.setFont(font);
        pnName.add(lblName);
        pnName.add(txtName);
        pnTextField.add(pnName);

        JPanel pnCategory = new TransparentPanel();
        lblCategory.setFont(font);
        cmbCategory.setFont(font);
        cmbCategory.setPreferredSize(txtID.getPreferredSize());
        pnCategory.add(lblCategory);
        pnCategory.add(cmbCategory);
        pnTextField.add(pnCategory);

        JPanel pnQuantity = new TransparentPanel();
        lblQuantity.setFont(font);
        txtQuantity.setFont(font);
        pnQuantity.add(lblQuantity);
        pnQuantity.add(txtQuantity);
        pnTextField.add(pnQuantity);

        JPanel pnUnits = new TransparentPanel();
        lblUnits.setFont(font);
        txtUnits.setFont(font);
        pnUnits.add(lblUnits);
        pnUnits.add(txtUnits);
        pnTextField.add(pnUnits);

        JPanel pnPrice = new TransparentPanel();
        lblPrice.setFont(font);
        txtPrice.setFont(font);
        pnPrice.add(lblPrice);
        pnPrice.add(txtPrice);
        pnTextField.add(pnPrice);

        Dimension lblSize = lblUnits.getPreferredSize();
        lblID.setPreferredSize(lblSize);
        lblName.setPreferredSize(lblSize);
        lblCategory.setPreferredSize(lblSize);
        lblQuantity.setPreferredSize(lblSize);
        lblUnits.setPreferredSize(lblSize);
        lblPrice.setPreferredSize(lblSize);

        pnThongTin.add(pnTextField);

        //=================PANEL ẢNH==========
        JPanel pnAnh = new TransparentPanel();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));

        JPanel pnImage = new TransparentPanel();
        pnImage.setPreferredSize(new Dimension((int) pnAnh.getPreferredSize().getWidth(), 250));
        lblProductImage = new JLabel();
        lblProductImage.setPreferredSize(new Dimension(200, 200));
        lblProductImage.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblProductImage.setIcon(getAnhSP(""));
        pnImage.add(lblProductImage);
        pnAnh.add(pnImage);

        JPanel pnButtonImage = new TransparentPanel();
        pnButtonImage.setPreferredSize(new Dimension(
                (int) pnImage.getPreferredSize().getHeight(), 40));
        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setFont(font);
        pnButtonImage.add(btnChonAnh);
        pnImage.add(pnButtonImage);

        pnThongTin.add(pnAnh);
        this.add(pnThongTin);

        JPanel pnButton = new TransparentPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        JPanel pnTimKiem = new TransparentPanel();
        JLabel lblTimKiem = new JLabel("Từ khoá tìm");
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(font);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        this.add(pnTimKiem);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnButton.add(btnTim);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);

        Dimension btnSize = btnTim.getPreferredSize();
        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);
        btnTim.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);

        this.add(pnButton);

        //============PANEL BẢNG===========
        JPanel pnTable = new TransparentPanel(new BorderLayout());
        //====================Bảng hàng hoá====================
        //<editor-fold defaultstate="collapsed" desc="Bảng sản phẩm">
        dtmProduct = new DefaultTableModel();
        dtmProduct.addColumn("Mã SP");
        dtmProduct.addColumn("Tên SP");
        dtmProduct.addColumn("Loại SP");
        dtmProduct.addColumn("Đơn giá");
        dtmProduct.addColumn("Số lượng");
        dtmProduct.addColumn("Đơn vị tính");
        dtmProduct.addColumn("Ảnh");
        tblProduct = new MyTable(dtmProduct);

        tblProduct.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblProduct.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblProduct.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblProduct.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TableColumnModel columnModelBanHang = tblProduct.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(77);
        columnModelBanHang.getColumn(1).setPreferredWidth(282);
        columnModelBanHang.getColumn(2).setPreferredWidth(120);
        columnModelBanHang.getColumn(3).setPreferredWidth(85);
        columnModelBanHang.getColumn(4).setPreferredWidth(138);
        columnModelBanHang.getColumn(5).setPreferredWidth(140);
        columnModelBanHang.getColumn(6).setPreferredWidth(0);

        JScrollPane scrTblSanPham = new JScrollPane(tblProduct);
        //</editor-fold>
        pnTable.add(scrTblSanPham, BorderLayout.CENTER);
        this.add(pnTable);

        loadDataCmbLoai();
        loadDataLenBangSanPham();
    }

    private void addEventsSanPham() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAnh("");
                loadDataLenBangSanPham();
                txtID.setText("");
                txtName.setText("");
                txtPrice.setText("");
                txtUnits.setText("");
                txtQuantity.setText("");
                cmbCategory.setSelectedIndex(0);
            }
        });

        tblProduct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSanPham();
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

        cmbCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddCategory();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddProduct();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEditProduct();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaSanPham();
            }
        });

        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });

        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });
        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatFileExcel();
            }
        });
        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyNhapFileExcel();
            }
        });
    }

//    private void xuLyNhapFileExcel() {
//        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
//        if (dlg.getAction() != MyDialog.OK_OPTION) {
//            return;
//        }
//
//        XuLyFileExcel nhapFile = new XuLyFileExcel();
//        nhapFile.nhapExcel(tblSanPham);
//
//        int row = tblSanPham.getRowCount();
//        for (int i = 0; i < row; i++) {
//            String ten = tblSanPham.getValueAt(i, 1) + "";
//            String loai = tblSanPham.getValueAt(i, 2) + "";
//            String donGia = tblSanPham.getValueAt(i, 3) + "";
//            String soLuong = tblSanPham.getValueAt(i, 4) + "";
//            String donViTinh = tblSanPham.getValueAt(i, 5) + "";
//            String anh = tblSanPham.getValueAt(i, 6) + "";
//
//            spBUS.inputProductFromExcel(ten, loai, soLuong, donViTinh, anh, donGia);
//        }
//    }
    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblProduct);
    }

    private void loadAnh(String anh) {
        lblProductImage.setIcon(getAnhSP(anh));
    }

    private void xuLyClickTblSanPham() {
        int row = tblProduct.getSelectedRow();
        if (row > -1) {
            String id = tblProduct.getValueAt(row, 0) + "";
            String name = tblProduct.getValueAt(row, 1) + "";
            String category = tblProduct.getValueAt(row, 2) + "";
            String price = tblProduct.getValueAt(row, 3) + "";
            String quantity = tblProduct.getValueAt(row, 4) + "";
            String units = tblProduct.getValueAt(row, 5) + "";
            String image = tblProduct.getValueAt(row, 6) + "";

            txtID.setText(id);
            txtName.setText(name);
            txtPrice.setText(price);
            txtQuantity.setText(quantity);
            txtUnits.setText(units.replace(",", ""));

            int flag = 0;
            for (int i = 0; i < cmbCategory.getItemCount(); i++) {
                if (cmbCategory.getItemAt(i).contains(category)) {
                    flag = i;
                    break;
                }
            }
            cmbCategory.setSelectedIndex(flag);
            loadAnh("image/Product/" + image);
        }
    }

    private void loadDataLenBangSanPham() {
        productBUS.readProducstList();
        dtmProduct.setRowCount(0);

        ArrayList<Product> productList = productBUS.getProductsList();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (Product product : productList) {
            Vector vec = new Vector();
            vec.add(product.getProductID());
            vec.add(product.getProductName());
            String categoryName = categoryBUS.getCategoryName(product.getProductCategoryID());
            vec.add(categoryName);
            vec.add(dcf.format(product.getPrice()));
            vec.add(dcf.format(product.getQuantity()));
            vec.add(product.getUnits());
            vec.add(product.getImage());
            dtmProduct.addRow(vec);
        }
    }

    private void loadDataCmbLoai() {
        cmbCategory.removeAllItems();

        ArrayList<Product_Category> dsl = categoryBUS.getCategoryList();
        cmbCategory.addItem("0 - Chọn loại");
        for (Product_Category loai : dsl) {
            cmbCategory.addItem(loai.getProductCategoryID() + " - " + loai.getProductCategoryName());
        }
        cmbCategory.addItem("Khác...");
    }

    private void handleAddCategory() {
        int x = cmbCategory.getSelectedIndex();
        if (x == cmbCategory.getItemCount() - 1) {
            DlgQuanLyLoai loaiGUI = new DlgQuanLyLoai();
            loaiGUI.setVisible(true);
            loadDataCmbLoai();
        }
    }

    private void handleAddProduct() {
        String anh = fileAnhSP.getName();
        boolean flag = productBUS.addProduct(
                cmbCategory.getSelectedItem() + "",
                txtName.getText(),
                "",
                txtPrice.getText(),
                txtQuantity.getText(),
                anh,
                txtUnits.getText());
        productBUS.readProducstList();
        loadDataLenBangSanPham();
        luuFileAnh();
    }
    File fileAnhSP;

    private void handleEditProduct() {
        String anh = fileAnhSP.getName();
        boolean flag = productBUS.edit(txtID.getText(),
                cmbCategory.getSelectedItem() + "",
                txtName.getText(),
                "",
                txtPrice.getText(),
                Integer.parseInt(txtQuantity.getText()),
                anh,
                txtUnits.getText()
        );
        productBUS.readProducstList();
        loadDataLenBangSanPham();
        luuFileAnh();
    }

    private void xuLyXoaSanPham() {
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = productBUS.deleteProduct(txtID.getText());
            if (flag) {
                loadDataLenBangSanPham();
            }
        }
    }

    private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("image/Product/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void xuLyChonAnh() {
        JFileChooser fileChooser = new MyFileChooser("image/Product/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblProductImage.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
    }

    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/Product/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

    private void xuLyTimKiem() {
        String ten = txtTimKiem.getText().toLowerCase();
        dtmProduct.setRowCount(0);
        ArrayList<Product> productList = null;
        productList = productBUS.getProductByName(ten);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (Product product : productList) {
            Vector vec = new Vector();
            vec.add(product.getProductID());
            vec.add(product.getProductName());
            String categoryName = categoryBUS.getCategoryName(product.getProductCategoryID());
            vec.add(categoryName);
            vec.add(dcf.format(product.getPrice()));
            vec.add(dcf.format(product.getQuantity()));
            vec.add(product.getUnits());
            vec.add(product.getImage());
            dtmProduct.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + productList.size(), MyDialog.INFO_DIALOG);
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
