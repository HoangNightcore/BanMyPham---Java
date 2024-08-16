/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.DTO;

/**
 *
 * @author acer
 */
public class User_Type {

    private String userTypeID;
    private String typeName;

    public User_Type() { }

    public User_Type(String userTypeID, String typeName) {
        this.userTypeID = userTypeID;
        this.typeName = typeName;
    }

    public String getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(String userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
