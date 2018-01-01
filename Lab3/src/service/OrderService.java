package service;

import model.Order;

import java.util.List;

public interface OrderService {

    public int getListOrderPageCountByUsername(String username);

    public List<Order> getListOrderByUsernameAndPage(String username, int page);

}
