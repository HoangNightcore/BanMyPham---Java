/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.ProductDAO;
import BanMyPham.DTO.Product;
import MyCustom.MyDialog;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class ProductBUS {

    private ArrayList<Product> productsList = null;
    private ProductDAO productDAO = new ProductDAO();

    public ProductBUS() {
        readProducstList();
    }

    public void readProducstList() {
        productsList = productDAO.getProductList();
    }

    public ArrayList<Product> getProductsList() {
        if (productsList == null) {
            readProducstList();
        }
        return productsList;
    }

    public Product getProduct(String productID) {
        if (!productID.trim().equals("")) {
            try {
                for (Product product : productsList) {
                    if (product.getProductID().equals(productID)) {
                        return product;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public ArrayList<Product> getProductByName(String name) {
        ArrayList<Product> prdLst = new ArrayList<>();
        for (Product product : productsList) {
            String productName = product.getProductName().toLowerCase();
            if (productName.toLowerCase().contains(name.toLowerCase())) {
                prdLst.add(product);
            }
        }
        return prdLst;
    }

    public ArrayList<Product> getProductByCategoryID(String categoryID) {
        if (!categoryID.trim().equals("")) {
            ArrayList<Product> prdLst = new ArrayList<>();
            try {
                for (Product product : productsList) {
                    if (product.getProductCategoryID().equals(categoryID)) {
                        prdLst.add(product);
                    }
                }
                return prdLst;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public String getImage(String productID) {
        return productDAO.getProductImage(productID);
    }

    public void addQuantityOfProduct(String productID, int quantity) {
        productDAO.addQuantityOfProduct(productID, quantity);
    }

    public boolean addProduct(String category, String productName, String description, String price, String quantity, String productImage, String units) {

        if (productName.trim().equals("")) {
            new MyDialog("Tên SP không được để rỗng!", MyDialog.ERROR_DIALOG);
            return false;
        }

//        if (quantity.equals("")) {
//            new MyDialog("Số lượng không được để rỗng!", MyDialog.ERROR_DIALOG);
//            return false;
//        } 
//        else if (Integer.parseInt(quantity) < 0) {
//            new MyDialog("Số lượng không được âm!", MyDialog.ERROR_DIALOG);
//            return false;
//        }

        if (units.trim().equals("")) {
            new MyDialog("Vui lòng điền Đơn vị tính!", MyDialog.ERROR_DIALOG);
            return false;
        }

//        if (price.equals("")) {
//            new MyDialog("Đơn giá không được để rỗng!", MyDialog.ERROR_DIALOG);
//            return false;
//        } else if (Double.parseDouble(price) < 0) {
//            new MyDialog("Đơn giá không được âm!", MyDialog.ERROR_DIALOG);
//            return false;
//        }

        try {
            String[] categoryTmp = category.split(" - ");
            String categoryID = categoryTmp[0];
            price = price.replace(",", "");
//            Double productPrice = Double.parseDouble(price);
//            int quantityProduct = Integer.parseInt(quantity);

            Double productPrice = 0.0;
            int quantityProduct = 0;
            
            if (categoryID.equals("0")) {
                new MyDialog("Vui lòng chọn Loại sản phẩm!", MyDialog.ERROR_DIALOG);
                return false;
            }
            Product product = new Product();
            product.setProductName(productName);
            product.setProductCategoryID(categoryID);
            product.setDescription(description);
            product.setQuantity(quantityProduct);
            product.setPrice(productPrice);
            product.setImage(productImage);
            product.setUnits(units);

            if (productDAO.addProduct(product)) {
                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new MyDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean inputProductFromExcel(String id,
            String category,
            String name,
            String description,
            double price,
            int quantity,
            String image,
            String units) {

        try {
            String[] categoryTmp = category.split(" - ");
            String categoryID = categoryTmp[0];

            Product product = new Product();
            product.setProductID(id);
            product.setProductName(name);
            product.setProductCategoryID(categoryID);
            product.setDescription(description);
            product.setQuantity(quantity);
            product.setUnits(units);
            product.setImage(image);
            product.setPrice(price);

            productDAO.inputProductFromExcel(product);
        } catch (Exception e) {
        }
        return false;
    }

    public boolean deleteProduct(String productID) {
        if (productID.trim().equals("")) {
            new MyDialog("Chưa chọn sản phẩm để xoá!", MyDialog.ERROR_DIALOG);
            return false;
        }

        if (productDAO.deleteProduct(productID)) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }

        new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        return false;
    }

    public boolean edit(String id,
            String category,
            String name,
            String description,
            String price,
            int quantity,
            String image,
            String units) {

        try {
            if (id.trim().equals("")) {
                new MyDialog("Chưa chọn sản phẩm để sửa!", MyDialog.ERROR_DIALOG);
                return false;
            }
            String[] categoryTmp = category.split(" - ");
            String categoryID = categoryTmp[0];
            price = price.replace(",", "");
            Double productPrice = Double.parseDouble(price);
            if (categoryID.equals("0")) {
                new MyDialog("Vui lòng chọn Loại sản phẩm!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (name.trim().equals("")) {
                new MyDialog("Tên SP không được để rỗng!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (units.trim().equals("")) {
                new MyDialog("Vui lòng điền Đơn vị tính!", MyDialog.ERROR_DIALOG);
                return false;
            }

            Product product = new Product();
            product.setProductID(id);
            product.setProductName(name);
            product.setProductCategoryID(categoryID);
            product.setDescription(description);
            product.setQuantity(quantity);
            product.setUnits(units);
            product.setImage(image);
            product.setPrice(productPrice);

            if (productDAO.editProduct(product)) {
                new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new MyDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public String getProductName(String productID) {
        for (Product product : productsList) {
            if (product.getProductID().equals(productID)) {
                return product.getProductName();
            }
        }
        return "";
    }
}
