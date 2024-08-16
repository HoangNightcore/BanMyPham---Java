/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DAO;

import MyCustom.MyDialog;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    public static Connection conn = null;
    private String severName;
    private String dbName;
    private String userName;
    private String password;

    public ConnectionDatabase(Component parentComponent) {
        readFileText();

        String url = "jdbc:sqlserver://" + severName + ":1433;databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";

        try {
            conn = DriverManager.getConnection(url, userName, password);
//            new MyDialog("Đã kết nối được tới CSDL!", MyDialog.SUCCESS_DIALOG);
        } catch (SQLException ex) {
            new MyDialog("Không kết nối được tới CSDL!", MyDialog.ERROR_DIALOG);
            System.exit(0);
        }
    }

    private void readFileText() {
        severName = "";
        dbName = "";
        userName = "";
        password = "";

        try {
            FileInputStream fis = new FileInputStream("ConnectFile.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            severName = br.readLine();
            dbName = br.readLine();
            userName = br.readLine();
            password = br.readLine();

            if (password == null) {
                password = "";
            }

        } catch (Exception e) {
        }
    }
}
