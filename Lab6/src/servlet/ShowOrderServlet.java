package servlet;

import action.business.OrderListBean;
import factory.ServiceFactory;
import model.User;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = req.getCookies();
        if (null != cookies) {
            for (Cookie c : cookies) {
                if (c.getName().equals("LoginCookie")) {
                    cookie = c;
                    break;
                }
            }
        }

        HttpSession session = req.getSession(false);

        if (session == null) {
            String usernameValue = req.getParameter("username");
            String passwordValue = req.getParameter("password");

            if (usernameValue != null) { // User is logging in
                User user = userService.findUser(usernameValue, passwordValue);
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
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }

                    // create a session to show that we are logged in
                    session = req.getSession(true);
                    session.setAttribute("username", usernameValue);
                    session.setAttribute("password", passwordValue);

                    req.setAttribute("username", usernameValue);
                    req.setAttribute("password", passwordValue);

                    ServletContext servletContext = getServletContext();
                    int logged = (int) servletContext.getAttribute("logged");
                    int guest = (int) servletContext.getAttribute("guest");
                    servletContext.setAttribute("logged", ++logged);
                    servletContext.setAttribute("guest", --guest);

                    setOrderListPage(req, resp, user);
                } else {
                    // Error page
                    getServletContext().getRequestDispatcher("/warning.jsp").forward(req, resp);
                }
            } else {
                // Display the login page. If the cookie exists, set login
                resp.sendRedirect(req.getContextPath() + "/Login");
            }
        } else {
            String usernameValue = (String) session.getAttribute("username");
            String passwordValue = (String) session.getAttribute("password");

            User user = userService.findUser(usernameValue, passwordValue);

            req.setAttribute("username", usernameValue);
            req.setAttribute("password", passwordValue);

            setOrderListPage(req, resp, user);
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
            OrderListBean orderListBean = new OrderListBean();
            orderListBean.setOrderList(orderService.getListOrderByUsernameAndPage(user.getUsername(), Integer.parseInt(req.getParameter("page"))));

            HttpSession session = req.getSession(false);
            session.setAttribute("list", orderListBean);
            session.setAttribute("totalNumber", orderService.getListOrderPageCountByUsername(user.getUsername()));

            getServletContext().getRequestDispatcher("/order.jsp").forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
