package listener;

import util.FileHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * Application Lifecycle Listener implementation class CounterListener
 * 安装在一个服务器中的一个特定URL名字空间（比如，/myapplication）下的
 * 所有Servlet，JSP，JavaBean等Web部件的集合构成了一个Web的应用，
 * 每一个Web应用（同一JVM），容器都会有一个背景对象，
 * 而javax.servlet.ServletContext接口就提供了访问这个背景对象的途径。
 */
@WebListener
public class CounterListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private ServletContext servletContext;

    private String counterFilePath = "/Users/Kray/Documents/Software Engineering/J2EE/Lab/Lab2/web/data/counter.txt";

    /**
     * 服务器启动
     *
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            List<String> input = FileHelper.readTxtFileLines(counterFilePath);
            assert input.size() == 2 : "存储人数的文件格式错误, 应该是 2 行数字";
            servletContext = servletContextEvent.getServletContext();
            servletContext.setAttribute("logged", Integer.parseInt(input.get(0)));
            servletContext.setAttribute("guest", Integer.parseInt(input.get(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        System.out.println("Create Session");
        servletContext.setAttribute("guest", ((int) servletContext.getAttribute("guest")) + 1);
        writeCounter(servletContext);
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        System.out.println("Destroy Session");
        servletContext.setAttribute("guest", ((int) servletContext.getAttribute("guest")) - 1);
        writeCounter(servletContext);
    }

    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("HttpSessionBindingEvent Added Name " + se.getName());
        if (se.getName().equals("username")) {
            servletContext.setAttribute("guest", ((int) servletContext.getAttribute("guest")) - 1);
            servletContext.setAttribute("logged", ((int) servletContext.getAttribute("logged") + 1));
            writeCounter(servletContext);
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("HttpSessionBindingEvent Removed Name " + se.getName());
        if (se.getName().equals("username")) {
            servletContext.setAttribute("guest", ((int) servletContext.getAttribute("guest")) + 1);
            servletContext.setAttribute("logged", ((int) servletContext.getAttribute("logged") - 1));
            writeCounter(servletContext);
        }
    }

    public void attributeReplaced(HttpSessionBindingEvent se) {
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }

    /**
     * 控制并发
     *
     * @param servletContext 上下文
     */
    private synchronized void writeCounter(ServletContext servletContext) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(counterFilePath));
            writer.write(Integer.toString((int) servletContext.getAttribute("logged")));
            writer.write("\n");
            writer.write(Integer.toString((int) servletContext.getAttribute("guest")));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
