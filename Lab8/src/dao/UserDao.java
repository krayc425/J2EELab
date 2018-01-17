package dao;

import model.User;

public interface UserDao {

    public User findUser(String username, String password);

}
