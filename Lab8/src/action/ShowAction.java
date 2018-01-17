package action;

import action.business.OrderListBean;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.apache.struts2.ServletActionContext.getServletContext;

public class ShowAction extends BaseAction {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    public String execute() throws IOException {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie c : cookies) {
                if (c.getName().equals("LoginCookie")) {
                    cookie = c;
                    break;
                }
            }
        }

        HttpSession session = request.getSession(false);

        if (session == null) {
            String usernameValue = request.getParameter("username");
            String passwordValue = request.getParameter("password");

            if (usernameValue != null) { // User is logging in
                User user = userService.findUser(usernameValue, passwordValue);
                if (user != null) {
                    if (cookie != null) { // If the cookie exists update the value only
                        // if changed
                        if (!usernameValue.equals(cookie.getValue())) {
                            cookie.setValue(usernameValue);
                            response.addCookie(cookie);
                        }
                    } else {
                        // If the cookie does not exist, create it and set value
                        cookie = new Cookie("LoginCookie", usernameValue);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }

                    // create a session to show that we are logged in
                    session = request.getSession(true);
                    session.setAttribute("username", usernameValue);
                    session.setAttribute("password", passwordValue);

                    request.setAttribute("username", usernameValue);
                    request.setAttribute("password", passwordValue);

                    ServletContext servletContext = getServletContext();
                    int logged = (int) servletContext.getAttribute("logged");
                    int guest = (int) servletContext.getAttribute("guest");
                    servletContext.setAttribute("logged", ++logged);
                    servletContext.setAttribute("guest", --guest);

                    setOrderListPage(request, response, user);

                    return SUCCESS;
                } else {
                    // Error page
                    return ERROR;
                }
            } else {
                // Display the login page. If the cookie exists, set login
                return LOGIN;
            }
        } else {
            String usernameValue = (String) session.getAttribute("username");
            String passwordValue = (String) session.getAttribute("password");

            User user = userService.findUser(usernameValue, passwordValue);

            request.setAttribute("username", usernameValue);
            request.setAttribute("password", passwordValue);

            setOrderListPage(request, response, user);

            return SUCCESS;
        }
    }

    /**
     * 设置订单页
     *
     * @param request
     * @param response
     * @param user
     */
    private void setOrderListPage(HttpServletRequest request, HttpServletResponse response, User user) {
        try {
            OrderListBean orderListBean = new OrderListBean();
            orderListBean.setOrderList(orderService.getListOrderByUsernameAndPage(user.getUsername(), Integer.parseInt(request.getParameter("page"))));

            HttpSession session = request.getSession(false);
            session.setAttribute("list", orderListBean);
            session.setAttribute("totalNumber", orderService.getListOrderPageCountByUsername(user.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
