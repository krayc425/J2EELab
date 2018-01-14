package service;

import dao.OrderDao;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements service.OrderService {

    @Autowired
    private OrderDao orderDao;

    public int getListOrderPageCountByUsername(String username) {
        return orderDao.getListOrderPageCountByUsername(username);
    }

    public List<Order> getListOrderByUsernameAndPage(String username, int page) {
        return orderDao.getListOrderByUsernameAndPage(username, page);
    }

}
