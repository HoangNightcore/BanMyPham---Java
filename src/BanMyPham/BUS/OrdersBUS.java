/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.OrdersDAO;
import BanMyPham.DTO.Orders;
import MyCustom.MyDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author acer
 */
public class OrdersBUS {

    private ArrayList<Orders> listOrders;
    private OrdersDAO orderDAO = new OrdersDAO();

    public ArrayList<Orders> getListOrders() {
        listOrders = orderDAO.getListOrders();
        return listOrders;
    }

    public void saveOrder( String userID, String customerID, String orderStatusID, double totalCost) {
        Orders order = new Orders();
        order.setUserID(userID);
        order.setCustomerID(customerID);
        order.setOrderStatusID(orderStatusID);
        order.setTotalCost(totalCost);

        orderDAO.addOrder(order);
    }

    public String getOrderIDLatest() {
        return orderDAO.getOrderIDLatest();
    }

    public Orders getOrderSameID(String orderID) {
        for (Orders order : listOrders) {
            if (order.getOrderID().equals(orderID)) {
                return order;
            }
        }
        return null;
    }

    public ArrayList<Orders> getOrderByCost(double min, double max) {
        try {
            ArrayList<Orders> lstOrds = new ArrayList<>();
            for (Orders order : listOrders) {
                if (order.getTotalCost() > min && order.getTotalCost() < max) {
                    lstOrds.add(order);
                }
            }
            return lstOrds;
        } catch (Exception e) {
            new MyDialog("Hãy nhập khoảng giá hợp lệ", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<Orders> getListHoaDonTheoNgay(String min, String max) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date minDate = sdf.parse(min);
            Date maxDate = sdf.parse(max);

            java.sql.Date dateMin = new java.sql.Date(minDate.getTime());
            java.sql.Date dateMax = new java.sql.Date(maxDate.getTime());

            ArrayList<Orders> lstOrds = orderDAO.getListOrders(dateMin, dateMax);
            return lstOrds;
        } catch (Exception e) {
            new MyDialog("Hãy nhập khoảng ngày hợp lệ!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }
}
