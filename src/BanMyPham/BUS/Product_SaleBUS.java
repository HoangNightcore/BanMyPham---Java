/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.Product_SaleDAO;
import BanMyPham.DTO.Product_Sale;
import MyCustom.MyDialog;
import java.util.ArrayList;
import java.util.Date;

public class Product_SaleBUS {

    private ArrayList<Product_Sale> salesList = null;
    private Product_SaleDAO saleDAO = new Product_SaleDAO();

    public Product_SaleBUS() {
        readSaleList();
    }

    public void readSaleList() {
        this.salesList = saleDAO.getSaleList();
    }

    public ArrayList<Product_Sale> getSaleList() {
        if (this.salesList == null) {
            readSaleList();
        }
        return this.salesList;
    }

    public boolean addSale(String name, String percent, String condition, Date startDate, Date endDate) {
        name = name.trim();
        percent = percent.replace("%", "");
        condition = condition.replace(",", "");
        if (name.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (percent.equals("")) {
            new MyDialog("Hãy nhập phần trăm giảm giá vào!", MyDialog.ERROR_DIALOG);
            return false;
        } else if (Double.parseDouble(percent) >= 100) {
            new MyDialog("Phần trăm giảm giá không được bằng hoặc vượt quá 100%", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (condition.equals("")) {
            new MyDialog("Hãy nhập điều kiện giảm giá vào!", MyDialog.ERROR_DIALOG);
            return false;
        } else if (Integer.parseInt(condition) < 0) {
            new MyDialog("Điều kiện giảm giá không được nhỏ hơn 0", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (startDate == null) {
            new MyDialog("Hãy nhập ngày bắt đầu giảm giá", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (endDate == null) {
            new MyDialog("Hãy nhập ngày kết thúc giảm giá", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (startDate.compareTo(endDate) > 0 || startDate.compareTo(endDate) == 0) {
            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            double discountPercentage = Double.parseDouble(percent);
            int conditionSale = Integer.parseInt(condition);

            Product_Sale sale = new Product_Sale();
            sale.setSaleName(name);
            sale.setDiscountPercentage(discountPercentage);
            sale.setCondition(conditionSale);

            sale.setStartDate(startDate);
            sale.setEndDate(endDate);

            flag = saleDAO.addSale(sale);
        } catch (Exception e) {
            new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean editSale(String id, String name, String percent, String condition, Date startDate, Date endDate, boolean isActive) {
        id = id.trim();
        name = name.trim();
        percent = percent.replace("%", "");
        condition = condition.replace(",", "");
        if (id.equals("")) {
            new MyDialog("Chưa chọn mã để sửa!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (name.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (startDate.compareTo(endDate) > 0 || startDate.compareTo(endDate) == 0) {
            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            double discountPercentage = Double.parseDouble(percent);
            int conditionSale = Integer.parseInt(condition);

            Product_Sale sale = new Product_Sale();
            sale.setSaleID(id);
            sale.setSaleName(name);
            sale.setDiscountPercentage(discountPercentage);
            sale.setCondition(conditionSale);

            sale.setStartDate(startDate);
            sale.setEndDate(endDate);
            sale.setIsActive(isActive);

            flag = saleDAO.editSale(sale);
        } catch (Exception e) {
            new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
