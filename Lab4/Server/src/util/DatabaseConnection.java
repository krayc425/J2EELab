package util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DatabaseConnection {

    //数据库连接地址
    private final static String URL = "jdbc:mysql://localhost:3306/J2EELab" +
            "?useUnicode=true&characterEncoding=utf8";

    private static InitialContext jndiContext = null;
    private static Connection connection = null;
    private static DataSource datasource = null;

    //用户名
    private final static String USERNAME = "root";
    //密码
    private final static String PASSWORD = "songkuixi";

    private final static String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName(DRIVER);
//        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        try {
            jndiContext = new InitialContext();
            datasource = (DataSource) jndiContext.lookup("java:jboss/datasources/mysqlDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return datasource.getConnection();
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
