package factory;

import dao.OrderDao;
import dao.OrderDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;

public class DaoFactory {

    public static OrderDao getOrderDao() {
        return OrderDaoImpl.getInstance();
    }

    public static UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

}
