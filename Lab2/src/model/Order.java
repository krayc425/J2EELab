package model;

import util.DatabaseConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {

    private static int PAGE_SIZE = 2;

    private int oid;
    private Date ordertime;
    private String ordername;
    private int ordercount;
    private double orderprice;
    private String username;
    private boolean isoutofstock;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public int getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(int ordercount) {
        this.ordercount = ordercount;
    }

    public double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(double orderprice) {
        this.orderprice = orderprice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIsoutofstock() {
        return isoutofstock;
    }

    public void setIsoutofstock(boolean isoutofstock) {
        this.isoutofstock = isoutofstock;
    }

    public static int getListOrderPageCountByUsername(String username) {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
    }

    public static ArrayList<Order> getListOrderByUsernameAndPage(String username, int page) {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //return result
        return resultOrder;
    }

}
