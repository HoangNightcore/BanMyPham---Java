/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import MyCustom.MyDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import BanMyPham.DAO.phieuNhapDAO;
import BanMyPham.DTO.Supplier;
import BanMyPham.DTO.Users;

import BanMyPham.DTO.WarehouseReceipt;
import BanMyPham.DTO.WarehouseReceipt_Details;

/**
 *
 * @author ASUS
 */
public class phieuNhapBUS {

    private phieuNhapDAO phieuNhapDAO = new phieuNhapDAO();
    private ArrayList<WarehouseReceipt> listPN = null;

    public phieuNhapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPN = phieuNhapDAO.getDSphieuNhap();
    }

    public ArrayList<WarehouseReceipt> getListPhieuNhap() {
        if (listPN == null) {
            docDanhSach();
        }
        return listPN;
    }

    public boolean themPhieuNhap(String nhaCungCap, String nhanVien, double tongTien) {
        String[] NCC = nhaCungCap.split(" - ");
        String[] NV = nhanVien.split(" - ");
        String maNCC = NCC[0];
        String maNV = NV[0];
        WarehouseReceipt pn = new WarehouseReceipt();
        pn.setSupplierID(maNCC);
        pn.setUserID(maNV);
        pn.setTotalCost(tongTien);
        return phieuNhapDAO.themPhieuNhap(pn);

    }

    public String getLastID() {
        return phieuNhapDAO.getLastID();
    }

    public WarehouseReceipt timPhieuNhap(String maPN) {
        for (WarehouseReceipt pn : listPN) {
            if (pn.getWarehouseReceiptID().equals(maPN)) {
                return pn;
            }
        }
        return null;
    }

}
