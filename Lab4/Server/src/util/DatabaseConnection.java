package util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DatabaseConnection {

    private static DataSource datasource = null;

    public static Connection getConnection() throws SQLException {
        try {
            InitialContext jndiContext = new InitialContext();
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
