
package BanMyPham.BUS;

import BanMyPham.DAO.User_TypeDAO;
import BanMyPham.DTO.User_Type;
import MyCustom.MyDialog;
import java.util.ArrayList;


public class User_TypeBUS {

    public static User_Type userType = null;
    private User_TypeDAO userTypeDAO = new User_TypeDAO();
    private ArrayList<User_Type> userTypeList = null;

    public void readUserTypeList() {
        this.userTypeList = userTypeDAO.getUserTypeList();
    }

    public void getUserType(String userTypeID) {
        userType = userTypeDAO.getUserType(userTypeID);
    }
    public String getUserTypeIDByName(String userTypeName){      
        userTypeName=userTypeName.trim();
        return userTypeDAO.getUserTypeIDByName(userTypeName);
    }

    public ArrayList<User_Type> getUserTypeList() {
        if (userTypeList == null) {
            readUserTypeList();
        }
        return this.userTypeList;
    }

    public boolean modifyUserType(String userTypeID, String typeName) {
        User_Type usertype = new User_Type(userTypeID, typeName);
        boolean flag = userTypeDAO.modifyUserType(usertype);
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean addUserType(String typeName) {
        if (typeName == null || typeName.trim().equals("")) {
            return false;
        }

        if (checkUserTypeeExist(typeName)) {
            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
            return false;
        }

        User_Type usertype = new User_Type();
        usertype.setTypeName(typeName.trim());
        boolean flag = userTypeDAO.addUserType(usertype);
        if (flag) {
            new MyDialog("Thêm thành công! Hãy hiệu chỉnh quyền", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    private boolean checkUserTypeeExist(String typeName) {
        readUserTypeList();
        for (User_Type ust : userTypeList) {
            if (ust.getTypeName().equalsIgnoreCase(typeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteUserType(String userTypeID) {
        boolean flag = userTypeDAO.deleteUserType(userTypeID);
        if (flag) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
