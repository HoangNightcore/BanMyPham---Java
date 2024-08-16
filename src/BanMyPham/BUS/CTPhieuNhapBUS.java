/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.CTPhieuNhapDAO;
import BanMyPham.DTO.WarehouseReceipt_Details;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class CTPhieuNhapBUS {

    private ArrayList<WarehouseReceipt_Details> listPhieuNhap = null;
    private CTPhieuNhapDAO ctPhieuNhapDAO = new CTPhieuNhapDAO();

    public CTPhieuNhapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPhieuNhap = ctPhieuNhapDAO.getListCTPhieuNhap();
    }

    public ArrayList<WarehouseReceipt_Details> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }

    public ArrayList<WarehouseReceipt_Details> getListPhieuNhap(String maPN) {
        ArrayList<WarehouseReceipt_Details> dsct = new ArrayList<>();
        if (listPhieuNhap == null) {
            return null;
        }
        for (WarehouseReceipt_Details ct : listPhieuNhap) {
            if (ct.getWarehouseReceiptID().equals(maPN)) { // Sử dụng equals để so sánh chuỗi
                dsct.add(ct);
            }
        }

        return dsct;
    }

    public boolean luuCTPhieuNhap(WarehouseReceipt_Details ctpn) {
        return ctPhieuNhapDAO.addCTPhieuNhap(ctpn);
    }

}
