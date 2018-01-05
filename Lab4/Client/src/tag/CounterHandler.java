package tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CounterHandler extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspContext jspContext = getJspContext();
        int logged = (int) jspContext.findAttribute("logged");
        int guest = (int) jspContext.findAttribute("guest");

        JspWriter writer = jspContext.getOut();
        writer.println("<p>Guest  " + guest + "</p>");
        writer.println("<p>Logged " + logged + "</p>");
        writer.println("<p>Total  " + (logged + guest) + "</p>");
    }

}
