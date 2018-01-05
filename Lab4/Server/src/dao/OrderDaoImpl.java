package dao;

import model.Order;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    private static OrderDaoImpl orderDao = new OrderDaoImpl();

    public static OrderDaoImpl getInstance() {
        return orderDao;
    }

    private static int PAGE_SIZE = 2;

    public int getListOrderPageCountByUsername(String username) {
        int count = -1;

        try {
            //获取数据
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM `order` WHERE username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }

            //关闭连接
            DatabaseConnection.close(result, stmt, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
    }

    public ArrayList<Order> getListOrderByUsernameAndPage(String username, int page) {
        ArrayList<Order> resultOrder = new ArrayList<>();
        try {
            //获取数据

            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM `order` WHERE username = ? LIMIT ?, ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, (page - 1) * PAGE_SIZE);
            stmt.setInt(3, PAGE_SIZE);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Order order = new Order();
                order.setOid(result.getInt("oid"));
                order.setUsername(username);
                order.setOrdercount(result.getInt("ordercount"));
                order.setOrdername(result.getString("ordername"));
                order.setOrderprice(result.getDouble("orderprice"));
                order.setOrdertime(result.getDate("ordertime"));
                order.setIsoutofstock(result.getInt("isoutofstock") == 1);

                resultOrder.add(order);
            }

            //关闭连接
            DatabaseConnection.close(result, stmt, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return result
        return resultOrder;
    }

}
