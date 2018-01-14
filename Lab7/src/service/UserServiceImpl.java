package service;

import dao.UserDao;
import factory.DaoFactory;
import model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private static UserServiceImpl userService = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return userService;
    }

    public UserServiceImpl() {
        userDao = DaoFactory.getUserDao();
    }

    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

}
