package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl userDao = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return userDao;
    }

    public User findUser(String username, String password) {
        User user = null;
        try {
            System.out.println("Username " + username + " Password " + password);

            //获取数据
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM `myuser` WHERE `username` = ? AND `password` = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();

            //遍历结果集
            if (result.first()) {
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
            }
            //关闭连接
            DatabaseConnection.close(result, stmt, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return result
        return user;
    }

}
