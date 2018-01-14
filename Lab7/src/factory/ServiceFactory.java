package factory;

import service.OrderService;
import service.OrderServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class ServiceFactory {

    public static OrderService getOrderService() {
        return OrderServiceImpl.getInstance();
    }

    public static UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

}
