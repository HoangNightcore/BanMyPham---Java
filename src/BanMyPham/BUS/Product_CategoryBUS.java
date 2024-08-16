
package BanMyPham.BUS;

import BanMyPham.DAO.Product_CategoryDAO;
import BanMyPham.DTO.Product_Category;
import MyCustom.MyDialog;
import java.util.ArrayList;


public class Product_CategoryBUS {

    private Product_CategoryDAO categoryDAO = new Product_CategoryDAO();
    private ArrayList<Product_Category> categoryList = null;

    public Product_CategoryBUS() {
        readCategoryList();
    }

    public void readCategoryList() {
        this.categoryList = categoryDAO.getProductCategoryList();
    }

    public ArrayList<Product_Category> getCategoryList() {
        if (categoryList == null) {
            readCategoryList();
        }
        return this.categoryList;
    }

    public String getCategoryName(String categoryID) {
        for (Product_Category category : categoryList) {
            if (category.getProductCategoryID().equals(categoryID)) {
                return category.getProductCategoryID()+ " - " + category.getProductCategoryName();
            }
        }
        return "";
    }

    public boolean addCategory(String categoryID, String categoryName) {
        if (categoryName.trim().equals("")) {
            new MyDialog("Không được để trống tên loại!", MyDialog.ERROR_DIALOG);
            return false;
        }
//        categoryID += 1;//Khởi tạo mã mới trong code
        Product_Category category = new Product_Category(categoryID, categoryName);
        if (categoryDAO.addProductCategory(category)) {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean deleteCategory(String categoryID) {
        if (categoryID.trim().equals("")) {
            new MyDialog("Chưa chọn loại để xoá!", MyDialog.SUCCESS_DIALOG);
            return false;
        }
        if (categoryDAO.deleteCategory(categoryID)) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Xoá thất bại! Loại có sản phẩm con", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean editCategory(String categoryID, String categoryName) {
        if (categoryName.trim().equals("")) {
            new MyDialog("Không được để trống tên loại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (categoryDAO.editCategory(categoryID, categoryName)) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }
}
