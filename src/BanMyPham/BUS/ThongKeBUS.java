/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.thongKeDAO;
import BanMyPham.DTO.ThongKe;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ThongKeBUS {

    public thongKeDAO thongKeDAO = new thongKeDAO();
    private ArrayList<Double> doanhThuThang;

    public ThongKe thongKe(int nam) {
        return thongKeDAO.getThongKe(nam);
    }

    public double getDoanhThuThang(int thang, int nam) {
        return thongKeDAO.getDoanhThuThang(thang, nam);
    }
}
