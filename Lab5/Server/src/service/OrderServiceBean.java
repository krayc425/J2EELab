package service;

import dao.OrderDao;
import factory.DaoFactory;
import model.Order;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderServiceBean implements OrderService {

    private OrderDao orderDao;

    public OrderServiceBean() {
        orderDao = DaoFactory.getOrderDao();
    }

    public int getListOrderPageCountByUsername(String username) {
        return orderDao.getListOrderPageCountByUsername(username);
    }

    public List<Order> getListOrderByUsernameAndPage(String username, int page) {
        return orderDao.getListOrderByUsernameAndPage(username, page);
    }

}
