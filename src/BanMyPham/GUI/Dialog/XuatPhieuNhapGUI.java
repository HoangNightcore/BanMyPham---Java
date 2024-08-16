package BanMyPham.GUI.Dialog;

import BanMyPham.BUS.CTPhieuNhapBUS;
import BanMyPham.BUS.phieuNhapBUS;
import BanMyPham.BUS.ProductBUS;
import BanMyPham.DTO.WarehouseReceipt_Details;
import BanMyPham.DTO.Product;
import MyCustom.MyDialog;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class XuatPhieuNhapGUI extends javax.swing.JDialog {

    private ArrayList<WarehouseReceipt_Details> listCTPhieuNhap = null;
    private int tongTien;
    private boolean checkNhap = false;
    String nhaCungCap;
    String nhanVien;

    public XuatPhieuNhapGUI(String nhaCungCap, String nhanVien, ArrayList<WarehouseReceipt_Details> listCTPhieuNhap) {
        Main.Main.changLNF("Windows");
        checkNhap = false;

        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;
        this.listCTPhieuNhap = listCTPhieuNhap;

        initComponents();

        for (WarehouseReceipt_Details ctpn : this.listCTPhieuNhap) {
            this.tongTien += ctpn.getTotalCost();
        }
        customPhieuNhap();
        txtChiTiet.setEditable(false);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    public boolean getCheckNhap() {
        return checkNhap;
    }

    @SuppressWarnings("unchecked")

    private void customPhieuNhap() {
        checkNhap = true;

        btnInPhieu.setEnabled(true);

        ProductBUS sanPhamBUS = new ProductBUS();
        ArrayList<Product> dssp = sanPhamBUS.getProductsList();

        txtChiTiet.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VND");

        // Lưu Phiếu nhập trước để xíu lấy cái mã
        phieuNhapBUS phieuNhapBUS = new phieuNhapBUS();
        phieuNhapBUS.themPhieuNhap(nhaCungCap, nhanVien, tongTien);

        String maPN = phieuNhapBUS.getLastID();
        CTPhieuNhapBUS ctPhieuNhapBUS = new CTPhieuNhapBUS();

        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:16pt"
                + "}"
                + "</style>";
        hd += "<h1 style='text-align:center;'>CHI TIẾT PHIẾU NHẬP</h1>";
        hd += "Nhân viên: " + nhanVien + "<br/>";
        hd += "Ngày lập: " + dtf.format(now) + "<br/>";
        hd += "Nhà cung cấp: " + nhaCungCap + "<br/>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%'>";
        hd += "<tr>"
                + "<th>Mã SP</th>"
                + "<th>Tên SP</th>"
                + "<th>Số lượng</th>"
                + "<th>Đơn giá</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        for (WarehouseReceipt_Details ctpn : listCTPhieuNhap) {
            hd += "<tr>";
            hd += "<td style='text-align:center;'>" + ctpn.getProductID() + "</td>";
            for (Product sp : dssp) {
                if (sp.getProductID().equals(ctpn.getProductID())) {
                    hd += "<td style='text-align:left;'>" + sp.getProductName() + "</td>";
                    break;
                }
            }

            hd += "<td style='text-align:center;'>" + ctpn.getQuantity() + "</td>";
            hd += "<td style='text-align:center;'>" + ctpn.getPrice() + "</td>";

            hd += "<td style='text-align:center;'>" + ctpn.getTotalCost() + "</td>";
            hd += "</tr>";

            //===================================================
            //===================LƯU CTPN VÀO DB=================
            //===================================================
            ctpn.setWarehouseReceiptID(maPN);
            ctPhieuNhapBUS.luuCTPhieuNhap(ctpn);
        }

        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Thành tiền</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";

        txtChiTiet.setText(hd);

        new MyDialog("Xác nhận thành công!", MyDialog.SUCCESS_DIALOG);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtChiTiet = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnInPhieu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtChiTiet.setEditable(false);
        jScrollPane1.setViewportView(txtChiTiet);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin phiếu nhập");
        jPanel1.add(jLabel1);

        btnInPhieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInPhieu.setText("In phiếu nhập");
        btnInPhieu.setPreferredSize(new java.awt.Dimension(145, 40));
        btnInPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuActionPerformed(evt);
            }
        });
        jPanel2.add(btnInPhieu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPhieuActionPerformed
        try {
            //Apache PDFBox
            txtChiTiet.print();
        } catch (PrinterException e) {
        }
        this.dispose();
    }//GEN-LAST:event_btnInPhieuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInPhieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane txtChiTiet;
    // End of variables declaration//GEN-END:variables
}
