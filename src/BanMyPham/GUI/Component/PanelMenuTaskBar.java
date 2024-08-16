/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package BanMyPham.GUI.Component;

import BanMyPham.DTO.User_Type;
import BanMyPham.DTO.Users;
import BanMyPham.GUI.DangNhapGUI;
import BanMyPham.GUI.MainGUI;
import BanMyPham.GUI.Panel.PanelQuanLyBanHang;
import BanMyPham.GUI.Panel.PanelQuanLyKhachHang;
import BanMyPham.GUI.Panel.PanelQuanLyKhuyenMai;
import BanMyPham.GUI.Panel.PanelQuanLyNhapHang;
import BanMyPham.GUI.Panel.PanelQuanLySanPham;
import BanMyPham.GUI.Panel.PanelQuanLyTaiKhoan;
import BanMyPham.GUI.Panel.PnQuanLyThongKeGUI;
import MyCustom.MyDialog;
import MyCustom.PanelGradient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author acer
 */
public class PanelMenuTaskBar extends javax.swing.JPanel {

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(255, 51, 102);
    Color NormalBackGroundColor = new Color(0, 204, 255);
    Color NormalGradientColor = new Color(51, 153, 255);

    JLabel lblUserType, lblUsername;
    JScrollPane scrollPane;
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;
    //Trả về frame parent đang chứa panel
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    PanelGradient listitem[];
    private PanelQuanLyBanHang panelQLBanHang;
    private PanelQuanLyKhuyenMai panelQLSale;
    private PanelQuanLyKhachHang panelQLKhachHang;
    private PanelQuanLySanPham panelQLSanPham;
    private PanelQuanLyTaiKhoan panelQLTaiKhoan;
    private PanelQuanLyNhapHang PanelQuanLyNhapHang;
    private PnQuanLyThongKeGUI pnQuanLyThongKeGUI;
    String[][] getSt = {
        {"Bán hàng", "icons8-cart-50.png", "banhang"},
        {"Khuyến mãi", "icons8-sale-50.png", "khuyenmai"},
        {"Sản phẩm", "icons8-cosmetics-50.png", "sanpham"},
        {"Nhân viên", "icons8-cashier-50.png", "nhanvien"},
        {"Khách hàng", "icons8-buyer-50.png", "khachhang"},
        {"Nhà cung cấp", "icons8-supplier-50.png", "nhacungcap"},
        {"Tài khoản", "icons8-account-management-50.png", "taikhoan"},
        {"Thống kê", "icons8-analytics-50.png", "thongke"},
        {"Đăng xuất", "icons8-log-out-50.png", "dangxuat"},};

    MainGUI main;
    private Users user = new Users();
    private User_Type currentUserType = new User_Type();

    public PanelMenuTaskBar(MainGUI main, Users user, User_Type currentUserType) {
        this.main = main;
        initComponents();
        this.user = user;
        this.currentUserType = currentUserType;
        inits();
    }

    private void inits() {
        int mainWidth = main.getWidth();
        int mainHeight = main.getHeight();

        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));

        // bar1, bar là các đường kẻ mỏng giữa taskbarMenu và MainContent
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 80));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));
        this.add(pnlTop, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BorderLayout(0, 0));
        pnlTop.add(info, BorderLayout.CENTER);

        // Cái info này bỏ vô cho đẹp tí, mai mốt có gì xóa đi đê hiển thị thông tin tài khoản và quyền
        in4(info);
        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 0));
        pnlTop.add(bar1, BorderLayout.EAST);

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(0, 1));
        pnlTop.add(bar2, BorderLayout.SOUTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 600));
        pnlCenter.setBackground(DefaultColor);
//        pnlCenter.setBorder(new EmptyBorder(0,15,0,35));
        pnlCenter.setLayout(new GridLayout(0, 1, 0, 5)); // GridLayout với một cột và khoảng cách 5px giữa các thành phần

        bar3 = new JPanel();
        bar3.setBackground(new Color(204, 214, 219));
        bar3.setPreferredSize(new Dimension(1, 1));
        this.add(bar3, BorderLayout.EAST);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 10));
        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar4 = new JPanel();
        bar4.setBackground(new Color(204, 214, 219));
        bar4.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar4, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);

        listitem = new PanelGradient[getSt.length];
        for (int i = 0; i < getSt.length; i++) {
            listitem[i] = new PanelGradient();
            if (currentUserType.getTypeName().equals("Nhân viên")) {
                if (getSt[i][0].equals("Khuyến mãi")
                        || getSt[i][0].equals("Sản phẩm")
                        || getSt[i][0].equals("Nhân viên")
                        || getSt[i][0].equals("Nhà cung cấp")
                        || getSt[i][0].equals("Thống kê")) {
                    continue;
                }
            } else if (currentUserType.getTypeName().equals("Quản lý")) {
                if (getSt[i][0].equals("Khuyến mãi")
                        || getSt[i][0].equals("Sản phẩm")) {
                    continue;
                }
            }
            listitem[i].itemMenuTaskbar(getSt[i][1], getSt[i][0]);
            pnlCenter.add(listitem[i]);
        }
        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].setColorGradient(Color.orange);
        listitem[0].isSelected = true;

        for (int i = 0; i < getSt.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }
        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                panelQLBanHang = new PanelQuanLyBanHang(user);
                main.setPanel(panelQLBanHang);
            }
        });
        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panelQLSale = new PanelQuanLyKhuyenMai();
                main.setPanel(panelQLSale);
            }
        });
        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panelQLSanPham = new PanelQuanLySanPham();
                main.setPanel(panelQLSanPham);
            }
        });
        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panelQLTaiKhoan = new PanelQuanLyTaiKhoan();
                main.setPanel(panelQLTaiKhoan);
            }
        });
        listitem[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panelQLKhachHang = new PanelQuanLyKhachHang();
                main.setPanel(panelQLKhachHang);
            }

        });
        listitem[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PanelQuanLyNhapHang = new PanelQuanLyNhapHang();
                main.setPanel(PanelQuanLyNhapHang);
            }

        });
        listitem[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnQuanLyThongKeGUI = new PnQuanLyThongKeGUI();
                main.setPanel(pnQuanLyThongKeGUI);
            }

        });
        listitem[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn đăng xuất?", MyDialog.WARNING_DIALOG);
                if (dlg.getAction() == MyDialog.OK_OPTION) {
                    DangNhapGUI login = new DangNhapGUI();
                    main.dispose();
                    login.setVisible(true);
                }
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
        public void in4(JPanel info) {
        JPanel pnlIcon = new JPanel(new MigLayout("fill", "40px[center]15px")); // Thiết lập insets để đặt khoảng cách 40px từ lề bên trái
        pnlIcon.setOpaque(false);
        info.add(pnlIcon, BorderLayout.WEST);

        JLabel lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(50, 70));

        if (user.getGender().equals("Nam")) {
            lblIcon.setIcon(new ImageIcon("image/Icon/icons8-male-user-50.png"));
        } else {
            lblIcon.setIcon(new ImageIcon("image/Icon/icons8-female-profile-50.png"));
        }

        pnlIcon.add(lblIcon);

        JPanel pnlInfo = new JPanel(new MigLayout("wrap 1, insets 15 0 0 0")); // Có thể thay đổi giá trị inset tùy thuộc vào khoảng cách mong muốn
        pnlInfo.setOpaque(false);
        info.add(pnlInfo, BorderLayout.CENTER);

        lblUsername = new JLabel(user.getFirstName());
        //lblUsername.setBorder(new EmptyBorder(0, 20, 0, 0));
        lblUsername.setFont(new Font("sansserif", Font.BOLD, 16));
        lblUsername.setForeground(Color.BLACK);
        pnlInfo.add(lblUsername, "gapbottom 0"); // Đặt khoảng cách dưới của lblUsername là 10 pixel

        lblUserType = new JLabel(currentUserType.getTypeName());
        lblUserType.setFont(new Font("sansserif", Font.PLAIN, 12));
        lblUserType.setForeground(Color.GRAY);
        pnlInfo.add(lblUserType);
//        lblIcon.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent evt) {
//                MyAccount ma = new MyAccount(owner, MenuTaskbar.this, "Chỉnh sửa thông tin tài khoản", true);
//            }
//        });
    }

    public void pnlMenuTaskbarMousePress(MouseEvent evt) {

        for (int i = 0; i < getSt.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
                listitem[i].setColorGradient(Color.orange);
//                listitem[i].setForeground(HowerFontColor);
            } else {
                listitem[i].isSelected = false;
//                listitem[i].setBackground(new Color(102, 204, 255));
//                listitem[i].setForeground(FontColor);
                listitem[i].setBackground(NormalBackGroundColor);
                listitem[i].setColorGradient(NormalGradientColor);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
