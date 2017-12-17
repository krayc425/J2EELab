package servlet;

import model.Order;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                    if (cookie != null) { // If the cookie exists update the value only
                        // if changed
                        if (!usernameValue.equals(cookie.getValue())) {
                            cookie.setValue(usernameValue);
                            resp.addCookie(cookie);
                        }
                    } else {
                        // If the cookie does not exist, create it and set value
                        cookie = new Cookie("LoginCookie", usernameValue);
                        cookie.setMaxAge(Integer.MAX_VALUE);
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
        ArrayList<Order> orders = Order.getListOrderByUsername(user.getUsername());

        try {
            if (orders.stream().anyMatch(
                    Order::isIsoutofstock
            )) {
                displayOutOfStockPage(req, res);
            } else {
                req.setAttribute("list", orders);

                displayOrderListPage(req, res);
                displayLogoutPage(req, res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayLogoutPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        out.println("<form method='GET' action='" + res.encodeURL(req.getContextPath() + "/Login") + "'>");
        out.println("</p>");
        out.println("<input type='submit' name='Logout' value='Logout'>");
        out.println("</form>");
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
            session = null;
        }
    }

    private void displayOutOfStockPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<p>You have at least 1 out-of-stock order!</p>");
        out.println("<input type='button' name='Back' value='Back' onclick='javascript:history.back()'>");
        out.println("</body></html>");
        
        HttpSession session = req.getSession();
        if (null != session) {
            System.out.println("session is not null, reseting");
            session.invalidate();
            session = null;
        }
    }

    private void displayOrderListPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ArrayList<Order> list = (ArrayList<Order>) req.getAttribute("list");

        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<table width='650' border='0' >");
        out.println("<tr>");
        out.println("<td width='650' height='80' background='" + req.getContextPath() + "/image/top.jpg'></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<p>Welcome " + req.getAttribute("username") + "</p>");

        out.println("My Order List:  ");

        list.forEach(
                o -> out.println(o.getOid())
        );
        out.println("</p>");

        out.println("Click <a href='" + res.encodeURL(req.getRequestURI()) + "'>here</a> to reload this page.<br>");
    }

}
