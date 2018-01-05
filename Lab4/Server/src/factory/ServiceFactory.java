package factory;

import service.OrderService;
import service.OrderServiceBean;
import service.UserService;
import service.UserServiceBean;

public class ServiceFactory {

    public static OrderService getOrderService() {
        return OrderServiceBean.getInstance();
    }

    public static UserService getUserService() {
        return UserServiceBean.getInstance();
    }

}
