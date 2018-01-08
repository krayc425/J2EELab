package dao;

import model.User;

import javax.ejb.Remote;

@Remote
public interface UserDao {

    public User findUser(String username, String password);

}
