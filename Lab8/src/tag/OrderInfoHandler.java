package tag;

import action.business.OrderListBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class OrderInfoHandler extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        OrderListBean orderListBean = (OrderListBean) getJspContext().findAttribute("list");
        JspWriter out = getJspContext().getOut();
        orderListBean.getOrderList().forEach(
                o -> {
                    try {
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
