package factory;

import dao.OrderDao;
import dao.OrderDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;

public class DaoFactory {

    public static OrderDao getOrderDao() {
        return (OrderDao) EJBFactory.getEJB("OrderDaoImpl", "dao.OrderDao");
    }

    public static UserDao getUserDao() {
        return (UserDao) EJBFactory.getEJB("UserDaoImpl", "dao.UserDao");
    }

}
