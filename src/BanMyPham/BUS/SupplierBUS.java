/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.SupplierDAO;
import BanMyPham.DTO.Supplier;
import MyCustom.MyDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author acer
 */
public class SupplierBUS {

    private SupplierDAO  SupplierDAO = new SupplierDAO();
    private ArrayList<Supplier> listNCC = null;

    public SupplierBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNCC = SupplierDAO.getListNCC();
    }

    public ArrayList<Supplier> getListNCC() {
        if (listNCC == null) {
            docDanhSach();
        }
        return listNCC;
    }
//
//    public boolean themPhieuNhap(String nhaCungCap, String nhanVien, int tongTien) {
//        String[] NCC = nhaCungCap.split(" - ");
//        String[] NV = nhanVien.split(" - ");
//
//        int maNCC = Integer.parseInt(NCC[0]);
//        int maNV = Integer.parseInt(NV[0]);
//
//        PhieuNhap pn = new PhieuNhap();
//        pn.setMaNCC(maNCC);
//        pn.setMaNV(maNV);
//        pn.setTongTien(tongTien);
//
//        return phieuNhapDAO.themPhieuNhap(pn);
//    }
//
//    public int getLastID() {
//        return phieuNhapDAO.getLastID();
//    }
//
//    public PhieuNhap timPhieuNhap(String maPN) {
//        int ma = Integer.parseInt(maPN);
//        for (PhieuNhap pn : listPhieuNhap) {
//            if (pn.getMaPN() == ma) {
//                return pn;
//            }
//        }
//        return null;
//    }
//
//    public ArrayList<PhieuNhap> getListPhieuNhapTheoGia(String giaThap, String giaCao) {
//        try {
//            int min = Integer.parseInt(giaThap);
//            int max = Integer.parseInt(giaCao);
//            if (max < min) {
//                new MyDialog("Hãy nhập khoảng giá phù hợp!", MyDialog.ERROR_DIALOG);
//                return null;
//            }
//            ArrayList<PhieuNhap> result = new ArrayList<>();
//            for (PhieuNhap pn : listPhieuNhap) {
//                if (pn.getTongTien() <= max && pn.getTongTien() >= min) {
//                    result.add(pn);
//                }
//            }
//            return result;
//        } catch (Exception e) {
//            new MyDialog("Hãy nhập số nguyên cho khoảng giá!", MyDialog.ERROR_DIALOG);
//        }
//        return null;
//    }
//
//    public ArrayList<PhieuNhap> getListPhieuNhapTheoNgay(String tuNgay, String denNgay) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
//
//            Date min = sdf.parse(tuNgay);
//            Date max = sdf.parse(denNgay);
//            if (max.before(min)) {
//                new MyDialog("Hãy nhập khoảng ngày phù hợp!", MyDialog.ERROR_DIALOG);
//                return null;
//            }
//            ArrayList<PhieuNhap> result = new ArrayList<>();
//            for (PhieuNhap pn : listPhieuNhap) {
//                if (pn.getNgayLap().after(min) && pn.getNgayLap().before(max)) {
//                    result.add(pn);
//                }
//            }
//            return result;
//        } catch (Exception e) {
//            new MyDialog("Hãy nhập ngày hợp lệ (dd/MM/yyy)!", MyDialog.ERROR_DIALOG);
//        }
//        return null;
//    }

}
