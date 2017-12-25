package listener;

import util.FileHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
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
public class CounterListener implements ServletContextListener, ServletContextAttributeListener {

    //总人数
    private int total;

    //已经登录
    private int logged;

    //游客人数
    private int guest;

    private String counterFilePath = "/Users/Kray/Documents/Software Engineering/J2EE/Lab/Lab2/web/data/counter.txt";

    /**
     * 服务器启动
     *
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            //读取本地文件信息
            System.out.println("Reading Start");
            List<String> input = FileHelper.readTxtFileLines(counterFilePath);
            assert input.size() == 3 : "存储人数的文件格式错误, 应该是3行数字";
            String loggedString = input.get(0);
            String guestString = input.get(1);
            String totalString = input.get(2);

            //获取人数
            this.logged = Integer.parseInt(loggedString);
            this.guest = Integer.parseInt(guestString);
            this.total = Integer.parseInt(totalString);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //设置属性
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("logged", this.logged);
        servletContext.setAttribute("guest", this.guest);
        servletContext.setAttribute("total", this.total);

        System.out.println("logged=" + logged + ", guest=" + guest + ", total=" + total);
        System.out.println("Application initialized");
    }

    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("ServletContext attribute added");
    }

    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("ServletContext attribute replaced");
        writeCounter(servletContextAttributeEvent);
    }

    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("ServletContext attribute removed");
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Application shut down");
    }

    /**
     * 控制并发
     * 保证在同一时刻最多只有一个线程执行该段代码
     * 当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。
     * 其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
     *
     * @param servletContextAttributeEvent
     */
    private synchronized void writeCounter(ServletContextAttributeEvent servletContextAttributeEvent) {
        ServletContext servletContext = servletContextAttributeEvent.getServletContext();
        this.guest = (int) servletContext.getAttribute("guest");
        this.logged = (int) servletContext.getAttribute("logged");
        this.total = (int) servletContext.getAttribute("total");
        //写入新的数据
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(counterFilePath));
            writer.write(Integer.toString(this.logged));
            writer.write("\n");
            writer.write(Integer.toString(this.guest));
            writer.write("\n");
            writer.write(Integer.toString(this.total));
            writer.close();
            System.out.println("Writing");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
