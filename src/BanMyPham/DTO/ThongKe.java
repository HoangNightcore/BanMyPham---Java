/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BanMyPham.DTO;

import java.util.ArrayList;

/**
 * @author User
 */
public class ThongKe {

    public int soLuongSP;
    public int soLuongKH;
    public int soLuongNV;
    public int[] tongThuQuy;
    public ArrayList<Product> topSanPhamBanChay;

    public ThongKe() {
    }


    public ThongKe(int soLuongSP, int soLuongKH, int soLuongNV, int[] tongThuQuy, ArrayList<Product> topSanPhamBanChay) {
        this.soLuongSP = soLuongSP;
        this.soLuongKH = soLuongKH;
        this.soLuongNV = soLuongNV;
        this.tongThuQuy = tongThuQuy;
        this.topSanPhamBanChay = topSanPhamBanChay;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(int soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public int getSoLuongNV() {
        return soLuongNV;
    }

    public void setSoLuongNV(int soLuongNV) {
        this.soLuongNV = soLuongNV;
    }

    public int[] getTongThuQuy() {
        return tongThuQuy;
    }

    public int getTongThuQuy(int quy) {
        return tongThuQuy[quy - 1];
    }

    public void setTongThuQuy(int[] tongThuQuy) {
        this.tongThuQuy = tongThuQuy;
    }

    public int getTongDoanhThu() {
        int tong = 0;
        for (int i = 0; i < 4; i++) {
            tong += tongThuQuy[i];
        }
        return tong;
    }

    public ArrayList<Product> getTopSanPhamBanChay() {
        return topSanPhamBanChay;
    }

    public void setTopSanPhamBanChay(ArrayList<Product> topSanPhamBanChay) {
        this.topSanPhamBanChay = topSanPhamBanChay;
    }
}
