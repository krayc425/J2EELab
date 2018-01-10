package dao;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao {

    public int getListOrderPageCountByUsername(String username);

    public List<Order> getListOrderByUsernameAndPage(String username, int page);

}
