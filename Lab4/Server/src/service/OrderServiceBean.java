package service;

import dao.OrderDao;
import factory.DaoFactory;
import model.Order;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderServiceBean implements OrderService {

    private OrderDao orderDao;

    private final static OrderServiceBean orderService = new OrderServiceBean();

    public static OrderServiceBean getInstance() {
        return orderService;
    }

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
