package BanMyPham.BUS;

import BanMyPham.DAO.Order_StatusDAO;
import BanMyPham.DTO.Order_Status;
import java.util.ArrayList;

public class Order_StatusBUS {

    private ArrayList<Order_Status> orderStatusList;
    private Order_StatusDAO ordStatusDAO = new Order_StatusDAO();

    public Order_StatusBUS() {
        readListOrdStatus();
    }

    public void readListOrdStatus() {
        this.orderStatusList = ordStatusDAO.getOrderStatusList();
    }

    public ArrayList<Order_Status> getOrdStatusList() {
        return orderStatusList;
    }
    public String getOrdStatusByName(String statusName){
        return ordStatusDAO.getOrderStatusListByStatusName(statusName);
    }
}
