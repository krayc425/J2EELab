package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //1 设置编码格式
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
        }
        //2 传递请求
        chain.doFilter(request, response);
    }

    public void destroy() {

    }

}
