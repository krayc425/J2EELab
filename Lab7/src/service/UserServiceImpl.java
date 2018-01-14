package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements service.UserService {

    @Autowired
    private UserDao userDao;

    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

}
