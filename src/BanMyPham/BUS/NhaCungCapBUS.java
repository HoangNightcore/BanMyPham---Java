/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.NhaCungCapDAO;
import BanMyPham.DTO.Supplier;
import MyCustom.MyDialog;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class NhaCungCapBUS {

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private ArrayList<Supplier> listNhaCungCap = null;

    public NhaCungCapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhaCungCap = nhaCungCapDAO.getListNCC();
    }

    public ArrayList<Supplier> getListNhaCungCap() {
        if (this.listNhaCungCap == null) {
            docDanhSach();
        }
        return this.listNhaCungCap;
    }

    public boolean thenNhaCung(String tenNCC, String diaChi, String SDT) {
        if (tenNCC.trim().equals("")) {
            new MyDialog("Hãy nhập tên nhà cung cấp !", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new MyDialog("Hãy nhập tên nhà cung cấp !", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (!SDT.matches("\\d+")) {
            new MyDialog("Số điện thoại chỉ được chứa các chữ số!", MyDialog.ERROR_DIALOG);
            return false;
        }

        if (SDT.length() != 10) {
            new MyDialog("Số điện thoại phải có đúng 10 chữ số!", MyDialog.ERROR_DIALOG);
            return false;
        }
        Supplier NCC = new Supplier();
        NCC.setSupplierName(tenNCC);
        NCC.setAddress(diaChi);
        NCC.setPhoneNumber(SDT);
        boolean flag = nhaCungCapDAO.AddNCC(NCC);
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới không thành công!", MyDialog.SUCCESS_DIALOG);

        }
        return flag;
    }

    public boolean suaNhaCungCap(String maNCC, String tenNCC, String diaChi, String dienThoai) {
        if (tenNCC.trim().equals("")) {
            new MyDialog("Hãy nhập tên Nhà cung cấp này!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new MyDialog("Hãy nhập địa chỉ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if (!pattern.matcher(dienThoai).matches()) {
            new MyDialog("Hãy nhập số điện thoại hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

        String ma = maNCC;

        Supplier ncc = new Supplier();
        ncc.setSupplierID(ma);
        ncc.setSupplierName(tenNCC);
        ncc.setAddress(diaChi);
        ncc.setPhoneNumber(dienThoai);

        boolean flag = nhaCungCapDAO.updateNCC(ncc);

        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean deleteNCC(String maNCC) {
        boolean result = nhaCungCapDAO.deleteNCC(maNCC);
        if (result) {
            new MyDialog("Bạn đã xóa thành công !", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Bạn đã xóa thất bại !", MyDialog.ERROR_DIALOG);
        }
        return result;
    }

}
