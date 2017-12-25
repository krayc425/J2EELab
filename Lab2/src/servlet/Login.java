package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = "";
        HttpSession session = request.getSession(false);

        // ServletContext
        ServletContext context = getServletContext();
        int total = (int) context.getAttribute("total");
        int logged = (int) context.getAttribute("logged");
        int guest = (int) context.getAttribute("guest");

        // 不是退出，游客人数加1
        if (null == request.getParameter("Logout")) {
            System.out.println("guest++\n");
            context.setAttribute("guest", ++guest);
            context.setAttribute("total", ++total);

            //用户退出，减去登录人数
        } else {
            if (null != session) {
                context.setAttribute("logged", --logged);
                context.setAttribute("total", ++total);

                session.invalidate();
            }
        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginCookie")) {
                    login = cookie.getValue();
                    break;
                }
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form method='POST' action='"
                + response.encodeURL(request.getContextPath() + "/ShowOrderServlet?page=1")
                + "'>");
        out.println("login: <input type='text' name='username' value='" + login + "'>");
        out.println("password: <input type='password' name='password' value=''>");
        out.println("<input type='submit' name='Submit' value='Submit'>");

        out.println("<p>Guest  " + guest + "</p>");
        out.println("<p>Logged " + logged + "</p>");
        out.println("<p>Total  " + total + "</p>");

        out.println("</form></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
