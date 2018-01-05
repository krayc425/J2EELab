package service;

import model.User;

import javax.ejb.Remote;

@Remote
public interface UserService {

    public User findUser(String username, String password);

}
