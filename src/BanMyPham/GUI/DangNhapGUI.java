package BanMyPham.GUI;

import BanMyPham.BUS.ServiceMail;
import BanMyPham.DAO.DangKyDAO;
import BanMyPham.DTO.ModelMessage;
import BanMyPham.GUI.Component.PanelCoverDangNhap;
import BanMyPham.DTO.Users;
import BanMyPham.GUI.Component.Message;
import BanMyPham.GUI.Component.PanelLoading;
import BanMyPham.GUI.Component.PanelVerifyCode;
import static Main.Main.changLNF;
import MyCustom.MyDialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class DangNhapGUI extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelCoverDangNhap cover;
    private PanelLoading loading;
    private PanelVerifyCode verifyCode;
    private final double addsize = 30;
    private final double coversize = 40; //Set chiều rộng cho panel cover (width = cover%)
    private final double loginSize = 60;
    private boolean isLogin; //Khởi tạo biến isLogin để kiểm tra xem đã login hay chưa
    private DangKyDAO dangKyDAO;

    private String strVerifyCode;

    private PanelLoginAndRegister loginAndRegister;

    private DecimalFormat df = new DecimalFormat("##0.###");

    public DangNhapGUI() {
        changLNF("Nimbus");
        initComponents();
        init();
    }

    private void init() {
        dangKyDAO = new DangKyDAO();
        layout = new MigLayout("fill, insets 0"); //set cho layout được fill toàn frame
        cover = new PanelCoverDangNhap();
        loading = new PanelLoading();
        verifyCode = new PanelVerifyCode();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                register();
            }
        };
        loginAndRegister = new PanelLoginAndRegister(eventRegister);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover; //Khởi tạo hệ số cho Cover
                double fractionLogin; //Khởi tạo hệ số cho Login
                double size = coversize; //Dùng để tạo hiệu ứng kéo dãn ra ở phần width của panel sau sự kiện
                if (fraction <= 0.5f) {
                    size += fraction * addsize;
                } else {
                    size += addsize - fraction * addsize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;  // FractionCover sẽ là 1 - fraction để ngược lại với hành động mặc định của animation
                    fractionLogin = fraction; //FractionLogin thì ngược lại với Cover
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) { //Nếu cover nằm ở phía bên trái thì sẽ show phần đăng ký ra
                    loginAndRegister.showLogin(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover)); //Fix lỗi: "AWT-EventQueue-0" java.lang.IllegalArgumentException: Illegal Constraint: 'pos 3.3795833587646484e-4al 0 n 100%' 
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%"); //fractionCover được sử dụng để xác định vị trí của cover trên layout dựa trên tỷ lệ hoàn thành của animation.
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate(); //điều chỉnh lại kích thước và vị trí của các thành phần con dựa trên các ràng buộc mới
            }

            @Override
            public void end() {
                isLogin = !isLogin; //Khi được gọi đến (thoát login) thì sẽ chuyển trạng thái lại
            }

        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f); //Bị lỗi: "AWT-EventQueue-0" java.lang.IllegalArgumentException: Illegal Constraint: 'pos 3.3795833587646484e-4al 0 n 100%' 
        animator.setResolution(0); //Giúp cho animation di chuyển mượt hơn
        bg.setLayout(layout);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(cover, "width " + coversize + "%, pos 0al 0 n 100%"); //set cho panel xanh lục chiếm khoảng 40% chiều rộng và 100% chiều dài
        bg.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%"); //1al as 100%
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {//Nếu sự kiện diễn ra mà animator chưa kích hoạt thì sẽ kích hoạt animator
                    animator.start();
                }
            }
        });
        verifyCode.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Users user = loginAndRegister.getUser();
                if (verifyCode.getInputCode().equals(strVerifyCode)) {
                    dangKyDAO.addRegisterUser(user);
                    showMessage(Message.MessageType.SUCCESS, "Register sucess");
                    verifyCode.setVisible(false);
                } else {
                    showMessage(Message.MessageType.ERROR, "Verify code incorrect");
                }
            }
        });
        verifyCode.addEventButtonCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyCode.setVisible(false);
            }
        });
    }

    private void register() {
        Users user = loginAndRegister.getUser();
        if (user.getUsername().equals("")) {
            showMessage(Message.MessageType.ERROR, "Không được để trống Username");
            return;
        }
        if (user.getEmail().equals("")) {
            showMessage(Message.MessageType.ERROR, "Không được để trống Email");
            return;
        }
        if (user.getPassword().equals("")) {
            showMessage(Message.MessageType.ERROR, "Không được để trống Password");
            return;
        }
        if (dangKyDAO.checkDuplicateUsername(user.getUsername())) {
            showMessage(Message.MessageType.ERROR, "Username đã tồn tại");
        } else if (dangKyDAO.checkDuplicateEmail(user.getEmail())) {
            showMessage(Message.MessageType.ERROR, "Email đã tồn tại");
        } else {
            sendMain(user);
        }
    }

    private void sendMain(Users user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                strVerifyCode = generateVerifyCode();
                loading.setVisible(true);
                ModelMessage ms = new ServiceMail().sendMain(user.getEmail(), strVerifyCode);
                if (ms.isSuccess()) {
                    loading.setVisible(false);
                    verifyCode.setVisible(true);
                } else {
                    loading.setVisible(false);
                    showMessage(Message.MessageType.ERROR, ms.getMessage());
                }
            }
        }).start();
    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    private String generateVerifyCode() {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        return code;
    }

    public void showWindows() {
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
