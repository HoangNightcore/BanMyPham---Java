/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import BanMyPham.DAO.ConnectionDatabase;
import BanMyPham.GUI.DangNhapGUI;
import javax.swing.JFrame;

/**
 *
 * @author acer
 */
public class Main {

    public static void main(String[] args) {
        changLNF("Nimbus");
        JFrame frame = new JFrame();
        new ConnectionDatabase(frame);

        DangNhapGUI login = new DangNhapGUI();
        login.showWindows();
    }

    /*
     *Thay đổi giao diện người dùng (Look and Feel - LNF) của JFrame.
     *
     * @param nameLNF tên của giao diện người dùng mới cần thiết lập
     */
    public static void changLNF(String nameLNF) {
        try {
            // Lặp qua danh sách các giao diện người dùng đã cài đặt sẵn
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                // Nếu tên của giao diện người dùng truyền vào khớp với một trong các giao diện đã cài đặt
                if (nameLNF.equals(info.getName())) {
                    // Thiết lập giao diện người dùng mới
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            // Xử lý các ngoại lệ nếu có
        }
    }
}
