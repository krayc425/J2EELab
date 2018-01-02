package servlet;

import factory.ServiceFactory;
import model.Order;
import model.User;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {

    private OrderService orderService;
    private UserService userService;

    public ShowOrderServlet() {
        super();
        orderService = ServiceFactory.getOrderService();
        userService = ServiceFactory.getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        String usernameValue = req.getParameter("username");
        String passwordValue = req.getParameter("password");

        if (usernameValue == null || usernameValue.equals("")) {
            usernameValue = (String) req.getAttribute("username");
        }
        if (passwordValue == null || passwordValue.equals("")) {
            passwordValue = (String) req.getAttribute("password");
        }
        System.out.println("Username " + usernameValue + " Password " + passwordValue);

        boolean isLoginAction = null != usernameValue;

        if (req.getSession() != null) { // User is logging in

            User user = userService.findUser(usernameValue, passwordValue);
            if (user != null) {
                // 登陆成功，人数+1
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
                    resp.addCookie(cookie);
                }

                // create a session to show that we are logged in
                HttpSession session = req.getSession(true);
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
    }

    /**
     * 设置订单页
     *
     * @param req
     * @param res
     * @param user
     */
    private void setOrderListPage(HttpServletRequest req, HttpServletResponse res, User user) {
        try {
            int count = orderService.getListOrderPageCountByUsername(user.getUsername());

            System.out.println("Set Order List Page " + count);

            List<Order> orders = orderService.getListOrderByUsernameAndPage(user.getUsername(), Integer.parseInt(req.getParameter("page")));
            req.setAttribute("list", orders);
            req.setAttribute("totalNumber", count);

            displayOrderListPage(req, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 错误页面
     *
     * @param req
     * @param res
     * @throws IOException
     */
    private void displayErrorPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            req.getRequestDispatcher("/warning.jsp").forward(req, res);
            HttpSession session = req.getSession();
            if (null != session) {
                session.invalidate();
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单列表界面
     *
     * @param req
     * @param res
     * @throws IOException
     */
    private void displayOrderListPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            req.getRequestDispatcher("/order.jsp").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
