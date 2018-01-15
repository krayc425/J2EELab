package action.business;

import model.Order;

import java.io.Serializable;
import java.util.List;

public class OrderListBean implements Serializable {

    private static final long serialID = 1L;

    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
