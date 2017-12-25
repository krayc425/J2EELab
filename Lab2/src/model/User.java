package model;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUser(String username, String password) {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //return result
        return user;
    }

}
