package BanMyPham.GUI.Dialog;

import BanMyPham.BUS.NhaCungCapBUS;
import BanMyPham.DTO.Supplier;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DlgChonNhaCungCap extends javax.swing.JDialog {

    private Supplier nhaCungCapChon = null;
    private NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();

    public DlgChonNhaCungCap() {
        initComponents();
        customControls();
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setResizable(false);
        btnXoa.setPreferredSize(new Dimension(100, 100));

    }

    private DefaultTableModel dtmNhaCungCap;

    private void customControls() {
        dtmNhaCungCap = new DefaultTableModel();
        dtmNhaCungCap.addColumn("Mã NCC");
        dtmNhaCungCap.addColumn("Tên NCC");
        dtmNhaCungCap.addColumn("Địa chỉ");
        dtmNhaCungCap.addColumn("Điện thoại");

        tblNhaCungCap.setModel(dtmNhaCungCap);
        tblNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(15);
        tblNhaCungCap.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblNhaCungCap.getColumnModel().getColumn(2).setPreferredWidth(111);
        tblNhaCungCap.getColumnModel().getColumn(3).setPreferredWidth(35);

        loadTableNhaCungCap();
        btnSuaNCC.setEnabled(false);
        btnXoa2.setEnabled(false);
//        jButton2.setPreferredSize(btnThemNCC.getPreferredSize());
    }

    public Supplier getNhaCungCap() {
        return nhaCungCapChon;
    }

    private void loadTableNhaCungCap() {
        dtmNhaCungCap.setRowCount(0);
        nhaCungCapBUS.docDanhSach();
        ArrayList<Supplier> dsncc = nhaCungCapBUS.getListNhaCungCap();

        if (dsncc != null) {
            for (Supplier ncc : dsncc) {
                Vector vec = new Vector();
                vec.add(ncc.getSupplierID());
                vec.add(ncc.getSupplierName());
                vec.add(ncc.getAddress());
                vec.add(ncc.getPhoneNumber());
                dtmNhaCungCap.addRow(vec);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhaCungCap = new MyTable();
        btnXoa = new javax.swing.JPanel();
        btnChonNCC = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();
        btnThemNCC = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Chọn nhà cung cấp");
        jPanel1.add(jLabel1);

        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Địa chỉ", "Điện thoại"
            }
        ));
        tblNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhaCungCap);

        btnChonNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChonNCC.setText("Chọn");
        btnChonNCC.setPreferredSize(new java.awt.Dimension(141, 40));
        btnChonNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNCCActionPerformed(evt);
            }
        });
        btnXoa.add(btnChonNCC);

        btnSuaNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSuaNCC.setText("Sửa thông tin");
        btnSuaNCC.setPreferredSize(new java.awt.Dimension(141, 40));
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });
        btnXoa.add(btnSuaNCC);

        btnThemNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemNCC.setText("Thêm mới");
        btnThemNCC.setPreferredSize(new java.awt.Dimension(141, 40));
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });
        btnXoa.add(btnThemNCC);

        btnXoa2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa2.setText("Xóa NCC");
        btnXoa2.setPreferredSize(new java.awt.Dimension(141, 40));
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });
        btnXoa.add(btnXoa2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNCCActionPerformed
        int row = tblNhaCungCap.getSelectedRow();
        if (row < 0) {
            new MyDialog("Vui lòng chọn nhà cung cấp", MyDialog.ERROR_DIALOG);
            return;
        } else {
            String maNCC = tblNhaCungCap.getValueAt(row, 0) + "";
            String tenNCC = tblNhaCungCap.getValueAt(row, 1) + "";
            String diaChiNCC = tblNhaCungCap.getValueAt(row, 2) + "";
            String sdtNCC = tblNhaCungCap.getValueAt(row, 3) + "";
            nhaCungCapChon = new Supplier(maNCC, tenNCC, diaChiNCC, sdtNCC);
            this.dispose();

        }
    }//GEN-LAST:event_btnChonNCCActionPerformed

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        DlgThemNhaCungCap dlg = new DlgThemNhaCungCap();
        dlg.setVisible(true);
        if (dlg.getCheckThemNCC()) {
            loadTableNhaCungCap();
        }
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        int row = tblNhaCungCap.getSelectedRow();
        if (row < 0) {
            new MyDialog("Ủa chưa chọn kìa!", MyDialog.ERROR_DIALOG);
            return;
        }

        Supplier ncc = new Supplier();
        ncc.setSupplierID(tblNhaCungCap.getValueAt(row, 0) + "");
        ncc.setSupplierName(tblNhaCungCap.getValueAt(row, 1) + "");
        ncc.setAddress(tblNhaCungCap.getValueAt(row, 2) + "");
        ncc.setPhoneNumber(tblNhaCungCap.getValueAt(row, 3) + "");

        DlgSuaNhaCungCap dlg = new DlgSuaNhaCungCap(ncc);
        dlg.setVisible(true);
        if (dlg.getCheckSuaNCC()) {
            loadTableNhaCungCap();
        }
        btnSuaNCC.setEnabled(false);
    }//GEN-LAST:event_btnSuaNCCActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
        int row = tblNhaCungCap.getSelectedRow();
        if (row < 0) {
            new MyDialog("Vui lòng chọn nhà cung cấp cần xóa !", MyDialog.ERROR_DIALOG);
        } else {
            String maNCC = tblNhaCungCap.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhà cung cấp này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean flag = nhaCungCapBUS.deleteNCC(maNCC);
                if (flag) {
                    loadTableNhaCungCap();
                }
            }
        }
        btnXoa2.setEnabled(false);
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void tblNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaCungCapMouseClicked
        // TODO add your handling code here:
        int row = tblNhaCungCap.getSelectedRow();
        if (row > -1) {
            btnSuaNCC.setEnabled(true);
            btnXoa2.setEnabled(true);
        }
    }//GEN-LAST:event_tblNhaCungCapMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNCC;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JPanel btnXoa;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhaCungCap;
    // End of variables declaration//GEN-END:variables
}
