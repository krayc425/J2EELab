package tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class CheckSessionHandler extends BodyTagSupport {

    private HttpServletResponse response;

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public int doEndTag() throws JspException {
        PageContext context = pageContext;
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpSession session = context.getSession();
        System.out.println(session);

        String usernameValue = (String) session.getAttribute("username");
        System.out.println(usernameValue);

        try {
            if (usernameValue == null) {
                System.out.println("Session Null After Body");
                response.sendRedirect("/Login");

                if (session != null) {
                    session.invalidate();
                }
                return SKIP_PAGE;
                //直到返回SKIP_BODY才继续往下执行 要求JSP容器忽略主体，进入下一步的处理工作。
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

}
