package BanMyPham.GUI;

import BanMyPham.BUS.User_TypeBUS;
import BanMyPham.DTO.User_Type;
import BanMyPham.DTO.Users;
import BanMyPham.GUI.Component.PanelMenuTaskBar;
import BanMyPham.GUI.Panel.PanelQuanLyBanHang;
import BanMyPham.GUI.Panel.PanelQuanLyKhuyenMai;
import Main.Main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class MainGUI extends javax.swing.JFrame {

    private Users user;
    PanelMenuTaskBar panelMenu;
    private User_Type currentUserType;

    public JPanel MainContent;
    private PanelQuanLyBanHang panelQLBanHang;
    private PanelQuanLyKhuyenMai panelQLSale;

    private int width;
    private int height;

    public MainGUI(Users user) {
        this.user = user;
        User_TypeBUS temp = new User_TypeBUS();
        temp.getUserType(user.getUserTypeID());
        this.currentUserType = temp.userType;

        initComponents();//Cần gọi lên trước các dòng code phía dưới để có thể hiển thị được ổn định
        this.setSize(new Dimension(1400, 840));
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Cửa hàng bán Mỹ Phẩm");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //Di chuyển frame ra trung tâm màn hình

        addControls();
        addEvents();
        getRootPane().setDefaultButton(null);
    }

    public void showWindow() {
        this.setVisible(true);
    }

    private void addControls() {
        width = this.getWidth();
        height = this.getHeight();

        /*
        ============================================================
                                TITLE BAR AND SIDE BAR
        ============================================================
         */
        panelMenu = new PanelMenuTaskBar(this, user, currentUserType);
        panelMenu.setPreferredSize(new Dimension((int) (width * 0.2), height));
        panelMenu.setVisible(true);
        this.add(panelMenu, BorderLayout.WEST);

        MainContent = new JPanel();
        MainContent.setPreferredSize(new Dimension((int) (width * 0.8), height));//Chỉnh vị trí của panel
        panelQLBanHang = new PanelQuanLyBanHang(user);
        MainContent = panelQLBanHang;
        this.add(MainContent, BorderLayout.CENTER);

    }

    private void addEvents() {

    }

    public void setPanel(JPanel pn) {
        MainContent.removeAll(); // Xóa tất cả các thành phần con của MainContent
        MainContent.add(pn); // Thêm panel mới vào MainContent
        MainContent.revalidate(); // Cập nhật giao diện hiển thị
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed


    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        panelQLBanHang.handleExit();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
