package dao;

import model.Order;

import javax.ejb.Remote;
import java.util.ArrayList;
import java.util.List;

@Remote
public interface OrderDao {

    public int getListOrderPageCountByUsername(String username);

    public List<Order> getListOrderByUsernameAndPage(String username, int page);

}
