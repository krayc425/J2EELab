package service;

import dao.OrderDao;
import factory.DaoFactory;
import model.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    private static OrderServiceImpl orderService = new OrderServiceImpl();

    public static OrderServiceImpl getInstance() {
        return orderService;
    }

    public OrderServiceImpl() {
        orderDao = DaoFactory.getOrderDao();
    }

    public int getListOrderPageCountByUsername(String username) {
        return orderDao.getListOrderPageCountByUsername(username);
    }

    public List<Order> getListOrderByUsernameAndPage(String username, int page) {
        return orderDao.getListOrderByUsernameAndPage(username, page);
    }

}
