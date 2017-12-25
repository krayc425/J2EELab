package servlet;

import com.sun.tools.corba.se.idl.constExpr.Or;
import model.Order;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {

    private int currentPage;

    public ShowOrderServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);

        Cookie cookie = null;
        Cookie[] cookies = req.getCookies();
        if (null != cookies) {
            // Look through all the cookies and see if the
            // cookie with the login info is there.

            for (Cookie c : cookies) {
                if (c.getName().equals("LoginCookie")) {
                    cookie = c;
                    break;
                }
            }
        }

        if (session == null) {
            String usernameValue = req.getParameter("username");
            String passwordValue = req.getParameter("password");

            boolean isLoginAction = null != usernameValue;

            System.out.println(usernameValue + " session null");

            if (isLoginAction) { // model.User is logging in

                User user = User.getUser(usernameValue, passwordValue);

                if (user != null) {
                    // 登陆成功，人数+1
                    this.increaseCounter();

                    if (cookie != null) { // If the cookie exists update the value only
                        // if changed
                        if (!usernameValue.equals(cookie.getValue())) {
                            cookie.setValue(usernameValue);
                            resp.addCookie(cookie);
                        }
                    } else {
                        // If the cookie does not exist, create it and set value
                        cookie = new Cookie("LoginCookie", usernameValue);
                        cookie.setMaxAge(0);
                        System.out.println("Add cookie");
                        resp.addCookie(cookie);
                    }

                    // create a session to show that we are logged in
                    session = req.getSession(true);
                    session.setAttribute("username", usernameValue);
                    session.setAttribute("password", passwordValue);

                    req.setAttribute("username", usernameValue);
                    req.setAttribute("password", passwordValue);


                    setOrderListPage(req, resp, user);
                } else {
                    // Error page
                    displayErrorPage(req, resp);
                }
            } else {
                // Display the login page. If the cookie exists, set login
                resp.sendRedirect(req.getContextPath() + "/Login");
            }
        } else {
            String usernameValue = (String) session.getAttribute("username");
            String passwordValue = (String) session.getAttribute("password");

            System.out.println(usernameValue + ", " + passwordValue);

            User user = User.getUser(usernameValue, passwordValue);
            req.setAttribute("username", usernameValue);
            req.setAttribute("password", passwordValue);

            setOrderListPage(req, resp, user);
        }
    }

    private void setOrderListPage(HttpServletRequest req, HttpServletResponse res, User user) {
        try {
            currentPage = Integer.parseInt(req.getParameter("page"));
            ArrayList<Order> orders = Order.getListOrderByUsernameAndPage(user.getUsername(), currentPage);
            req.setAttribute("list", orders);

            displayOrderListPage(req, res);

            displayCountPage(req, res, Order.getListOrderPageCountByUsername(user.getUsername()));

            displayLogoutPage(req, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayCountPage(HttpServletRequest req, HttpServletResponse res, int count) throws IOException {
        System.out.println(count + " Pages");

        PrintWriter out = res.getWriter();
        out.println("<p>");

        int i = 0;
        while (i < count) {
            out.print("<a href='" + res.encodeURL(req.getContextPath() + "/ShowOrderServlet?page=") + (i + 1)
                    + "'> " + (i + 1) + " </a>");
            i++;
        }

        out.println("</p>");
    }

    private void displayLogoutPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        out.println("<form method='GET' action='" + res.encodeURL(req.getContextPath() + "/Login") + "'>");
        out.println("<input type='submit' name='Logout' value='Logout'>");
        out.println("</form>");

        // ServletContext
        ServletContext Context = getServletContext();
        int total = (int) Context.getAttribute("total");
        int logged = (int) Context.getAttribute("logged");
        int guest = (int) Context.getAttribute("guest");
        out.println("<p>Guest  " + guest + "</p>");
        out.println("<p>Logged " + logged + "</p>");
        out.println("<p>Total  " + total + "</p>");

        out.println("</body></html>");
    }

    private void displayErrorPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<p>Wrong username and password!</p>");
        out.println("<input type='button' name='Back' value='Back' onclick='javascript:history.back()'>");
        out.println("</body></html>");

        HttpSession session = req.getSession();
        if (null != session) {
            System.out.println("session is not null, reseting");
            session.invalidate();
        }
    }

    private void displayOrderListPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ArrayList<Order> list = (ArrayList<Order>) req.getAttribute("list");

        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<p>Welcome " + req.getAttribute("username") + "</p>");
        out.println("<p>订单列表: </p><table>");
        out.println("<tr>");
        out.println("<td>" + "Id" + "</td>");
        out.println("<td>" + "Time" + "</td>");
        out.println("<td>" + "Name " + "</td>");
        out.println("<td>" + "Count" + "</td>");
        out.println("<td>" + "Price" + "</td>");
        out.println("<td>" + "Out of Stock" + "</td>");
        out.println("</tr>");

        list.forEach(
                o -> {
                    out.println("<tr>");
                    out.println("<td>" + o.getOid() + "</td>");
                    out.println("<td>" + o.getOrdertime() + "</td>");
                    out.println("<td>" + o.getOrdername() + "</td>");
                    out.println("<td>" + o.getOrdercount() + "</td>");
                    out.println("<td>" + o.getOrderprice() + "</td>");
                    if (o.isIsoutofstock()) {
                        out.println("<td>" + "True" + "</td>");
                    }
                    out.println("</tr>");
                }
        );

        out.println("</table></p>");
        out.println("Click <a href='" + res.encodeURL(req.getRequestURI() + "?page=" + currentPage) + "'>here</a> to reload this page.<br>");
    }

    /**
     * 增加已经登录的人数和总人数
     */
    private void increaseCounter() {
        ServletContext Context = getServletContext();
        int total = (int) Context.getAttribute("total");
        int logged = (int) Context.getAttribute("logged");
        System.out.println("show servlet total = " + total);
        Context.setAttribute("logged", ++logged);
        Context.setAttribute("total", ++total);
    }

}
