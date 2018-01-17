package service;

import model.User;

public interface UserService {

    public User findUser(String username, String password);

}
