package BanMyPham.GUI.Dialog;

import BanMyPham.BUS.Product_CategoryBUS;
import BanMyPham.DTO.Product_Category;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class DlgQuanLyLoai extends javax.swing.JDialog {

    DefaultTableModel dtmCategory;

    public DlgQuanLyLoai() {
        initComponents();
        dtmCategory = new DefaultTableModel();
        dtmCategory.addColumn("Mã loại");
        dtmCategory.addColumn("Tên loại");
        tblCategory.setModel(dtmCategory);
        loadDataLenTblLoai();
        this.setLocationRelativeTo(null);
    }

    Product_CategoryBUS categoryBUS = new Product_CategoryBUS();

    private void loadDataLenTblLoai() {
        dtmCategory.setRowCount(0);
        ArrayList<Product_Category> categoryList = categoryBUS.getCategoryList();
        if (categoryList != null) {
            for (Product_Category loai : categoryList) {
                Vector vec = new Vector();
                vec.add(loai.getProductCategoryID());
                vec.add(loai.getProductCategoryName());
                dtmCategory.addRow(vec);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategory = new MyTable();
        jPanel3 = new javax.swing.JPanel();
        pnCategoryID = new javax.swing.JPanel();
        lblCategoryID = new javax.swing.JLabel();
        txtCategoryID = new javax.swing.JTextField();
        pnCategoryName = new javax.swing.JPanel();
        lblCategoryName = new javax.swing.JLabel();
        txtCategoryName = new javax.swing.JTextField();
        pnButton = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setModal(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ LOẠI");
        jPanel1.add(jLabel1);

        tblCategory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại", "Tên loại"
            }
        ));
        tblCategory.getTableHeader().setReorderingAllowed(false);
        tblCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategory);

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        lblCategoryID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCategoryID.setText("Mã loại");
        lblCategoryID.setPreferredSize(new java.awt.Dimension(63, 22));
        pnCategoryID.add(lblCategoryID);

        txtCategoryID.setEditable(false);
        txtCategoryID.setColumns(15);
        txtCategoryID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnCategoryID.add(txtCategoryID);

        jPanel3.add(pnCategoryID);

        lblCategoryName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCategoryName.setText("Tên loại");
        pnCategoryName.add(lblCategoryName);

        txtCategoryName.setColumns(15);
        txtCategoryName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnCategoryName.add(txtCategoryName);

        jPanel3.add(pnCategoryName);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(80, 35));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnButton.add(btnThem);

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(80, 35));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnButton.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setPreferredSize(new java.awt.Dimension(80, 35));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnButton.add(btnXoa);

        jPanel3.add(pnButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
            .addComponent(pnTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryMouseClicked
        int row = tblCategory.getSelectedRow();
        if (row > -1) {
            String categoryID = tblCategory.getValueAt(row, 0) + "";
            String categoryName = tblCategory.getValueAt(row, 1) + "";
            txtCategoryID.setText(categoryID);
            txtCategoryName.setText(categoryName);
        }
    }//GEN-LAST:event_tblCategoryMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (categoryBUS.addCategory(txtCategoryID.getText(), txtCategoryName.getText())) {
            loadDataLenTblLoai();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            String ma = txtCategoryID.getText();
            if (categoryBUS.deleteCategory(ma)) {
                loadDataLenTblLoai();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String id = txtCategoryID.getText();
        String name = txtCategoryName.getText();
        if (categoryBUS.editCategory(id, name)) {
            loadDataLenTblLoai();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoryID;
    private javax.swing.JLabel lblCategoryName;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnCategoryID;
    private javax.swing.JPanel pnCategoryName;
    private javax.swing.JPanel pnTable;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTextField txtCategoryID;
    private javax.swing.JTextField txtCategoryName;
    // End of variables declaration//GEN-END:variables
}
