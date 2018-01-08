package servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class Login extends HttpServlet {

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String login;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginCookie")) {
                    login = cookie.getValue();
                    request.setAttribute("username", login);
                    break;
                }
            }
        }

        boolean isLogout = request.getParameter("Logout") != null;
        boolean isBack = request.getParameter("Back") != null;

        ServletContext servletContext = getServletContext();
        int logged = (int) servletContext.getAttribute("logged");
        int guest = (int) servletContext.getAttribute("guest");
        if (!isLogout && !isBack) {
            servletContext.setAttribute("guest", ++guest);
        }

        //用户退出,才能减去登录人数(防止用户重复刷新登出界面)
        if (isLogout) {
            servletContext.setAttribute("logged", --logged);
            servletContext.setAttribute("guest", ++guest);
            //remove session
            if (session != null) {
                session.invalidate();
                session = null;
            }
        }

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
