package util;

import java.sql.*;
public class DatabaseConnection {

    //数据库连接地址
    private final static String URL = "jdbc:mysql://localhost:3306/J2EELab" +
            "?useUnicode=true&characterEncoding=utf8";

    //用户名
    private final static String USERNAME = "root";
    //密码
    private final static String PASSWORD = "songkuixi";

    private final static String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        rs.close();
        ps.close();
        conn.close();
    }

    public static void close(ResultSet rs, PreparedStatement ps) throws SQLException {
        rs.close();
        ps.close();
    }
}
