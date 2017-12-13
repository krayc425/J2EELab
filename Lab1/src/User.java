import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int uid;

    private String username;

    private String password;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

//    static User getUserByUsername(String username) {
//        Connection connection = null;
//        PreparedStatement stmt = null;
//        ResultSet result = null;
//        ArrayList list = new ArrayList();
//        try {
//            connection = datasource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            stmt = connection.prepareStatement("select oid from user_order where uid=?");
//            stmt.setString(1, (String) req.getAttribute("login"));
//            result = stmt.executeQuery();
//            while (result.next()) {
//                Order order = new Order();
//                order.setOid(result.getInt("oid"));
//
//                list.add(order);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
