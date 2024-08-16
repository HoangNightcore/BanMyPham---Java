/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanMyPham.BUS;

import BanMyPham.DAO.Order_DetailsDAO;
import BanMyPham.DTO.Order_Details;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Order_DetailsBUS {

    private ArrayList<Order_Details> listOrderDetails;
    private Order_DetailsDAO orderDetailsDAO = new Order_DetailsDAO();
    private OrdersBUS ordersBUS = new OrdersBUS();

    public Order_DetailsBUS() {
        readListOrdDetails();
    }

    public void readListOrdDetails() {
        this.listOrderDetails = orderDetailsDAO.getOrderDetailsList();
    }

    public ArrayList<Order_Details> getOrderDetailsList() {
        return listOrderDetails;
    }

    public ArrayList<Order_Details> getListOrderDetalsToOrderID(String orderID) {
        ArrayList<Order_Details> ordDtlList = new ArrayList<>();

        for (Order_Details order : listOrderDetails) {
            if (order.getOrderID().equals(orderID)) {
                ordDtlList.add(order);
            }
        }

        return ordDtlList;
    }

    public void addOrderDetail(String productID, int quantity) {
        String orderID =ordersBUS.getOrderIDLatest();
        
        Order_Details orderDetail = new Order_Details();
        orderDetail.setOrderID(orderID);
        orderDetail.setProductID(productID);
        orderDetail.setQuantity(quantity);

        orderDetailsDAO.addOrderDetail(orderDetail);
    }
}
