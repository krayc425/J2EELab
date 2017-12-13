import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

@WebServlet("/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DataSource datasource = null;

    public void init() {
        InitialContext jndiContext = null;
        Properties properties = new Properties();
        properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        try {
            jndiContext = new InitialContext(properties);
            datasource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/J2EELab");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        boolean cookieFound = false;
        Cookie cookie = null;
        Cookie[] cookies = req.getCookies();
        if (null != cookies) {
            // Look through all the cookies and see if the
            // cookie with the login info is there.
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("LoginCookie")) {
                    cookieFound = true;
                    break;
                }
            }
        }

        if (session == null) {
            String loginValue = req.getParameter("login");
            boolean isLoginAction = (null == loginValue) ? false : true;
            System.out.println(loginValue + " session null");
            if (isLoginAction) { // User is logging in
                if (cookieFound) { // If the cookie exists update the value only
                    // if changed
                    if (!loginValue.equals(cookie.getValue())) {
                        cookie.setValue(loginValue);
                        resp.addCookie(cookie);
                    }
                } else {
                    // If the cookie does not exist, create it and set value
                    cookie = new Cookie("LoginCookie", loginValue);
                    cookie.setMaxAge(Integer.MAX_VALUE);
                    System.out.println("Add cookie");
                    resp.addCookie(cookie);
                }

                // create a session to show that we are logged in
                session = req.getSession(true);
                session.setAttribute("login", loginValue);

                req.setAttribute("login", loginValue);

                getOrderList(req, resp);
                displayOrderlistPage(req, resp);
                displayLogoutPage(req, resp);
            } else {
                System.out.println(loginValue + " session null");
                // Display the login page. If the cookie exists, set login
                resp.sendRedirect(req.getContextPath() + "/Login");
            }
        } else {
            String loginValue = (String) session.getAttribute("login");
            System.out.println(loginValue + " session");

            req.setAttribute("login", loginValue);
            getOrderList(req, resp);
            displayOrderlistPage(req, resp);
            displayLogoutPage(req, resp);
        }
    }

    private void getOrderList(HttpServletRequest req, HttpServletResponse res) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList list = new ArrayList();
        try {
            connection = datasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt = connection.prepareStatement("select oid from user_order where uid=?");
            stmt.setString(1, (String) req.getAttribute("login"));

            System.out.println("Uid" + (String) req.getAttribute("login"));

            result = stmt.executeQuery();
            while (result.next()) {
                System.out.println("Order");
                Order order = new Order();
                order.setOid(result.getInt("oid"));

                list.add(order);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.setAttribute("list", list);
    }

    public void displayLogoutPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        out.println("<form method='GET' action='" + res.encodeURL(req.getContextPath() + "/Login") + "'>");
        out.println("</p>");
        out.println("<input type='submit' name='Logout' value='Logout'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    public void displayOrderlistPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ArrayList list = (ArrayList) req.getAttribute("list");

        // resp.sendRedirect(req.getContextPath()+"/MyStockList");

        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<table width='650' border='0' >");
        out.println("<tr>");
        out.println("<td width='650' height='80' background='" + req.getContextPath() + "/image/top.jpg'>&nbsp;</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<p>Welcome " + req.getAttribute("login") + "</p>");

        out.println("My Order List:  ");
        System.out.println("Order List");
        for (int i = 0; i < list.size(); i++) {
            Order order = (Order) list.get(i);
            out.println(order.getOid());
        }
        out.println("</p>");

        out.println("Click <a href='" + res.encodeURL(req.getRequestURI()) + "'>here</a> to reload this page.<br>");
    }

}
