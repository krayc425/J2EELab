package service;

import model.Order;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface OrderService {

    public int getListOrderPageCountByUsername(String username);

    public List<Order> getListOrderByUsernameAndPage(String username, int page);

}
