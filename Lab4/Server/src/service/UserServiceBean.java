package service;

import dao.UserDao;
import factory.DaoFactory;
import model.User;

import javax.ejb.Stateless;

@Stateless
public class UserServiceBean implements UserService {

    private UserDao userDao;

    private final static UserServiceBean userService = new UserServiceBean();

    public static UserServiceBean getInstance() {
        return userService;
    }

    public UserServiceBean() {
        userDao = DaoFactory.getUserDao();
    }

    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

}
