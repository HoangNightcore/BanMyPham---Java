package BanMyPham.GUI.Dialog;

import BanMyPham.BUS.User_TypeBUS;
import BanMyPham.BUS.UsersBUS;
import BanMyPham.DTO.User_Type;
import MyCustom.MyDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class DlgUserType_Password extends javax.swing.JDialog {

    private String userID;

    public DlgUserType_Password(String maNV) {
        this.userID = maNV;
        initComponents();
        this.setTitle("Chỉnh sửa tài khoản");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
        this.setIconImage(icon);

        loadDataCmbQuyen();
    }
    private User_TypeBUS userTypeBUS = new User_TypeBUS();
    private UsersBUS usersBUS = new UsersBUS();

    private void loadDataCmbQuyen() {
        txtPass_UserID.setText(userID);
        txtUserType_UserID.setText(userID);

        String username = usersBUS.getUsernameByUserID(userID);
        if (username.equals("")) {
            new MyDialog("Nhân viên này chưa có tài khoản!", MyDialog.ERROR_DIALOG);
            btnCapMatKhau.setEnabled(false);
            btnLuuQuyen.setEnabled(false);
        }
        txtPass_Username.setText(username);

        cmbQuyen.removeAllItems();
        userTypeBUS.readUserTypeList();
        ArrayList<User_Type> dsq = userTypeBUS.getUserTypeList();
        for (User_Type pq : dsq) {
            cmbQuyen.addItem(pq.getTypeName());
        }

        String quyen = usersBUS.getUserTypeIDByUserID(userID);
        for (int i = 0; i < cmbQuyen.getItemCount(); i++) {
            if (cmbQuyen.getItemAt(i).equals(quyen)) {
                cmbQuyen.setSelectedIndex(i);
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTab = new javax.swing.JTabbedPane();
        pnCapMatKhau = new javax.swing.JPanel();
        pnTitleMatKhau = new javax.swing.JPanel();
        lblTitleMatKhau = new javax.swing.JLabel();
        pnMatKhauInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPass_UserID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass_Username = new javax.swing.JTextField();
        pnMatKhauButton = new javax.swing.JPanel();
        btnCapMatKhau = new javax.swing.JButton();
        pnCapMatKhau2 = new javax.swing.JPanel();
        pnTitleMatKhau2 = new javax.swing.JPanel();
        lblTitleMatKhau2 = new javax.swing.JLabel();
        pnMatKhauInfo2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtUserType_UserID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pnMatKhauButton2 = new javax.swing.JPanel();
        btnLuuQuyen = new javax.swing.JButton();
        cmbQuyen = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnTab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        pnCapMatKhau.setLayout(new javax.swing.BoxLayout(pnCapMatKhau, javax.swing.BoxLayout.Y_AXIS));

        lblTitleMatKhau.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitleMatKhau.setText("Cấp lại mật khẩu");
        pnTitleMatKhau.add(lblTitleMatKhau);

        pnCapMatKhau.add(pnTitleMatKhau);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Mã Nhân viên");

        txtPass_UserID.setEditable(false);
        txtPass_UserID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Tên đăng nhập");

        txtPass_Username.setEditable(false);
        txtPass_Username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnCapMatKhau.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCapMatKhau.setText("Đặt lại mật khẩu");
        btnCapMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapMatKhauActionPerformed(evt);
            }
        });
        pnMatKhauButton.add(btnCapMatKhau);

        javax.swing.GroupLayout pnMatKhauInfoLayout = new javax.swing.GroupLayout(pnMatKhauInfo);
        pnMatKhauInfo.setLayout(pnMatKhauInfoLayout);
        pnMatKhauInfoLayout.setHorizontalGroup(
            pnMatKhauInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMatKhauInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMatKhauInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnMatKhauInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass_Username)
                    .addComponent(txtPass_UserID))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMatKhauInfoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnMatKhauButton, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnMatKhauInfoLayout.setVerticalGroup(
            pnMatKhauInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMatKhauInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMatKhauInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPass_UserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnMatKhauInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPass_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnMatKhauButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnCapMatKhau.add(pnMatKhauInfo);

        pnTab.addTab("Cấp lại mật khẩu", pnCapMatKhau);

        pnCapMatKhau2.setLayout(new javax.swing.BoxLayout(pnCapMatKhau2, javax.swing.BoxLayout.Y_AXIS));

        lblTitleMatKhau2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitleMatKhau2.setText("Chỉnh sửa quyền");
        pnTitleMatKhau2.add(lblTitleMatKhau2);

        pnCapMatKhau2.add(pnTitleMatKhau2);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Mã Nhân viên");

        txtUserType_UserID.setEditable(false);
        txtUserType_UserID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Quyền Tài Khoản");

        btnLuuQuyen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLuuQuyen.setText("Lưu thay đổi");
        btnLuuQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuQuyenActionPerformed(evt);
            }
        });
        pnMatKhauButton2.add(btnLuuQuyen);

        cmbQuyen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnMatKhauInfo2Layout = new javax.swing.GroupLayout(pnMatKhauInfo2);
        pnMatKhauInfo2.setLayout(pnMatKhauInfo2Layout);
        pnMatKhauInfo2Layout.setHorizontalGroup(
            pnMatKhauInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMatKhauInfo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMatKhauInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnMatKhauInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserType_UserID)
                    .addComponent(cmbQuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMatKhauInfo2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnMatKhauButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnMatKhauInfo2Layout.setVerticalGroup(
            pnMatKhauInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMatKhauInfo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMatKhauInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUserType_UserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnMatKhauInfo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnMatKhauButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnCapMatKhau2.add(pnMatKhauInfo2);

        pnTab.addTab("Chỉnh sửa quyền", pnCapMatKhau2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTab)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapMatKhauActionPerformed
        usersBUS.resetPassword(userID, txtPass_Username.getText());

        this.setVisible(false);
    }//GEN-LAST:event_btnCapMatKhauActionPerformed

    private void btnLuuQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuQuyenActionPerformed
        usersBUS.datLaiQuyen(userID, userTypeBUS.getUserTypeIDByName(cmbQuyen.getSelectedItem() + ""));
        this.setVisible(false);
    }//GEN-LAST:event_btnLuuQuyenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapMatKhau;
    private javax.swing.JButton btnLuuQuyen;
    private javax.swing.JComboBox<String> cmbQuyen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTitleMatKhau;
    private javax.swing.JLabel lblTitleMatKhau2;
    private javax.swing.JPanel pnCapMatKhau;
    private javax.swing.JPanel pnCapMatKhau2;
    private javax.swing.JPanel pnMatKhauButton;
    private javax.swing.JPanel pnMatKhauButton2;
    private javax.swing.JPanel pnMatKhauInfo;
    private javax.swing.JPanel pnMatKhauInfo2;
    private javax.swing.JTabbedPane pnTab;
    private javax.swing.JPanel pnTitleMatKhau;
    private javax.swing.JPanel pnTitleMatKhau2;
    private javax.swing.JTextField txtPass_UserID;
    private javax.swing.JTextField txtPass_Username;
    private javax.swing.JTextField txtUserType_UserID;
    // End of variables declaration//GEN-END:variables
}
