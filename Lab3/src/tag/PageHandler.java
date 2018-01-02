package tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PageHandler extends SimpleTagSupport {

    private String totalNumber;

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();

        HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
        HttpServletResponse response =
                (HttpServletResponse) pageContext.getResponse();

        JspWriter writer = pageContext.getOut();
        writer.println("<p>");

        int i = 0;
        while (i < Integer.parseInt(getTotalNumber())) {
            writer.print("<a href='" + response.encodeURL(request.getContextPath() + "/ShowOrderServlet?page=") + (i + 1)
                    + "'> " + (i + 1) + " </a>");
            i++;
        }
        writer.println("</p>");
    }

}
