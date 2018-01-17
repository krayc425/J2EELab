# J2EELab

Lab for J2EE and Middleware Technology.

# 客户订单查询应用

## [Lab1：](https://github.com/songkuixi/J2EELab/tree/master/Lab1)Servlet + Session + JDBC

* 客户登录，根据客户的 ID / PW，查询订单情况，并根据具体情况，返回不同结果（可在 1 个 Servlet 中实现）：
    * 未知的客户 ID：错误页面
    * 正常结果：标准页面
        * 每一项订单：包括多项属性，如时间、名称、数量、价格等
        * 分页显示（选做）
    * 有缺货的订单项：警示页面

## [Lab2：](https://github.com/songkuixi/J2EELab/tree/master/Lab2)Servlet + Session + JDBC + Filter + Listener

* 使用过滤器解决表单中的中文请求后的乱码问题
* 统计在线人数：
    * 总人数
    * 已登录人数
    * 游客人数
* 思考：用户关闭浏览器，未点击“退出”，如何计算人数？

## [Lab3：](https://github.com/songkuixi/J2EELab/tree/master/Lab3)MVC (DAO + Service + JavaBean + Servlet + JSP + Tag)

* 基于 MVC、DAO、Service 等设计
    * Model：JavaBean
        * ServiceFactory, XXXService
        * DAOFactory，XXXDAO
    * View：JSP
    * Controller: Servlet
* 验证用户状态
    * 已登录用户可访问，未登录用户转向登录页面
        * 用户通过 URL 直接访问 XXXServlet 或 XXX.JSP 时
        
## [Lab4：](https://github.com/songkuixi/J2EELab/tree/master/Lab4)DAO:EJB + Service:EJB + JavaBean + Servlet + JSP

* Service 层：使用 EJB 技术
* DAO 层：使用 EJB 技术

特别感谢：[J2EE第5次作业的坑，我帮你踩过了：](https://samperson1997.github.io/2018/01/05/J2EE5/)

## [Lab5：](https://github.com/songkuixi/J2EELab/tree/master/Lab5)DAO:EJB + Service:EJB + JPA + Servlet + JSP + Tag

* 修改 Lab4 中数据访问层和 Model 的设计
    * Model
        * Entity Beans
    * DAO
        * Session Beans
        * JPA EntityManager

## [Lab6：](https://github.com/songkuixi/J2EELab/tree/master/Lab6)Hibernate (Hibernate/Entity,DAO + Service + Servlet + JSP)

* 修改 Lab3 中数据访问层和 Model 的设计
    * Model
        * Hibernate Entity Beans
    * DAO
        * Hibernate Session
    * Service
        * 不使用 EJB 技术

## [Lab7：](https://github.com/songkuixi/J2EELab/tree/master/Lab7)Spring (Hibernate/Entity + Spring/Service,DAO + Servlet + JSP)

* 修改 Lab6 中数据访问层和 Service 的设计
    * Model
        * Hibernate Entity Beans
    * DAO
        * Spring
    * Service
        * Spring

## [Lab8：](https://github.com/songkuixi/J2EELab/tree/master/Lab8)Struts (Hibernate/Entity + Spring/Service,DAO + Struts/JSP)

* 修改 Lab7 中 Servlet 及 JSP
    * Model
        * Hibernate Entity Beans
    * DAO
        * Spring
    * Service
        * Spring
    * Action
        * Struts2

# 完整目录树

以便对应查找一些 `jar` 包或配置文件

```
.
├── Homework1.md
├── Lab1
│   ├── Lab1.iml
│   ├── out
│   │   └── artifacts
│   │       └── Lab1_war_exploded
│   │           ├── META-INF
│   │           │   ├── MANIFEST.MF
│   │           │   └── context.xml
│   │           ├── WEB-INF
│   │           │   ├── classes
│   │           │   │   ├── model
│   │           │   │   │   ├── Order.class
│   │           │   │   │   └── User.class
│   │           │   │   ├── servlet
│   │           │   │   │   ├── Login.class
│   │           │   │   │   ├── Reset.class
│   │           │   │   │   └── ShowOrderServlet.class
│   │           │   │   └── util
│   │           │   │       └── DatabaseConnection.class
│   │           │   ├── lib
│   │           │   │   ├── jstl-1.2.jar
│   │           │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │           │   │   ├── servlet-api.jar
│   │           │   │   └── standard-1.1.2.jar
│   │           │   └── web.xml
│   │           └── index.jsp
│   ├── sql
│   │   └── Lab1.sql
│   ├── src
│   │   ├── model
│   │   │   ├── Order.java
│   │   │   └── User.java
│   │   ├── servlet
│   │   │   ├── Login.java
│   │   │   ├── Reset.java
│   │   │   └── ShowOrderServlet.java
│   │   └── util
│   │       └── DatabaseConnection.java
│   └── web
│       ├── META-INF
│       │   ├── MANIFEST.MF
│       │   └── context.xml
│       ├── WEB-INF
│       │   ├── classes
│       │   │   ├── model
│       │   │   │   ├── Order.class
│       │   │   │   └── User.class
│       │   │   ├── servlet
│       │   │   │   ├── Login.class
│       │   │   │   ├── Reset.class
│       │   │   │   └── ShowOrderServlet.class
│       │   │   └── util
│       │   │       └── DatabaseConnection.class
│       │   ├── lib
│       │   │   ├── jstl-1.2.jar
│       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   ├── servlet-api.jar
│       │   │   └── standard-1.1.2.jar
│       │   └── web.xml
│       └── index.jsp
├── Lab2
│   ├── Lab2.iml
│   ├── out
│   │   └── artifacts
│   │       └── Lab2_war_exploded
│   │           ├── META-INF
│   │           │   ├── MANIFEST.MF
│   │           │   └── context.xml
│   │           ├── WEB-INF
│   │           │   ├── classes
│   │           │   │   ├── filter
│   │           │   │   │   └── CharacterEncodingFilter.class
│   │           │   │   ├── listener
│   │           │   │   │   └── CounterListener.class
│   │           │   │   ├── model
│   │           │   │   │   ├── Order.class
│   │           │   │   │   └── User.class
│   │           │   │   ├── servlet
│   │           │   │   │   ├── Login.class
│   │           │   │   │   ├── Reset.class
│   │           │   │   │   └── ShowOrderServlet.class
│   │           │   │   └── util
│   │           │   │       ├── DatabaseConnection.class
│   │           │   │       └── FileHelper.class
│   │           │   ├── lib
│   │           │   │   ├── jstl-1.2.jar
│   │           │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │           │   │   ├── servlet-api.jar
│   │           │   │   └── standard-1.1.2.jar
│   │           │   └── web.xml
│   │           ├── data
│   │           │   └── counter.txt
│   │           └── index.jsp
│   ├── sql
│   │   └── Lab2.sql
│   ├── src
│   │   ├── filter
│   │   │   └── CharacterEncodingFilter.java
│   │   ├── listener
│   │   │   └── CounterListener.java
│   │   ├── model
│   │   │   ├── Order.java
│   │   │   └── User.java
│   │   ├── servlet
│   │   │   ├── Login.java
│   │   │   ├── Reset.java
│   │   │   └── ShowOrderServlet.java
│   │   └── util
│   │       ├── DatabaseConnection.java
│   │       └── FileHelper.java
│   └── web
│       ├── META-INF
│       │   ├── MANIFEST.MF
│       │   └── context.xml
│       ├── WEB-INF
│       │   ├── classes
│       │   │   ├── filter
│       │   │   │   └── CharacterEncodingFilter.class
│       │   │   ├── listener
│       │   │   │   └── CounterListener.class
│       │   │   ├── model
│       │   │   │   ├── Order.class
│       │   │   │   └── User.class
│       │   │   ├── servlet
│       │   │   │   ├── Login.class
│       │   │   │   ├── Reset.class
│       │   │   │   └── ShowOrderServlet.class
│       │   │   └── util
│       │   │       ├── DatabaseConnection.class
│       │   │       └── FileHelper.class
│       │   ├── lib
│       │   │   ├── jstl-1.2.jar
│       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   ├── servlet-api.jar
│       │   │   └── standard-1.1.2.jar
│       │   └── web.xml
│       ├── data
│       │   └── counter.txt
│       └── index.jsp
├── Lab3
│   ├── Lab3.iml
│   ├── out
│   │   └── artifacts
│   │       └── Lab3_war_exploded
│   │           ├── META-INF
│   │           │   ├── MANIFEST.MF
│   │           │   └── context.xml
│   │           ├── WEB-INF
│   │           │   ├── classes
│   │           │   │   ├── action
│   │           │   │   │   └── business
│   │           │   │   │       └── OrderListBean.class
│   │           │   │   ├── bean
│   │           │   │   │   └── OrderListBean.class
│   │           │   │   ├── dao
│   │           │   │   │   ├── OrderDao.class
│   │           │   │   │   ├── OrderDaoImpl.class
│   │           │   │   │   ├── UserDao.class
│   │           │   │   │   └── UserDaoImpl.class
│   │           │   │   ├── factory
│   │           │   │   │   ├── DaoFactory.class
│   │           │   │   │   └── ServiceFactory.class
│   │           │   │   ├── filter
│   │           │   │   │   └── CharacterEncodingFilter.class
│   │           │   │   ├── listener
│   │           │   │   │   └── CounterListener.class
│   │           │   │   ├── model
│   │           │   │   │   ├── Order.class
│   │           │   │   │   └── User.class
│   │           │   │   ├── service
│   │           │   │   │   ├── OrderService.class
│   │           │   │   │   ├── OrderServiceImpl.class
│   │           │   │   │   ├── UserService.class
│   │           │   │   │   └── UserServiceImpl.class
│   │           │   │   ├── servlet
│   │           │   │   │   ├── Login.class
│   │           │   │   │   ├── Reset.class
│   │           │   │   │   └── ShowOrderServlet.class
│   │           │   │   ├── tag
│   │           │   │   │   ├── CheckSessionHandler.class
│   │           │   │   │   ├── CounterHandler.class
│   │           │   │   │   ├── OrderInfoHandler.class
│   │           │   │   │   └── PageHandler.class
│   │           │   │   └── util
│   │           │   │       ├── DatabaseConnection.class
│   │           │   │       └── FileHelper.class
│   │           │   ├── lib
│   │           │   │   ├── jstl-1.2.jar
│   │           │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │           │   │   ├── servlet-api.jar
│   │           │   │   └── standard-1.1.2.jar
│   │           │   ├── order
│   │           │   ├── tlds
│   │           │   │   └── songkuixi.tld
│   │           │   └── web.xml
│   │           ├── data
│   │           │   └── counter.txt
│   │           ├── index.jsp
│   │           ├── login.jsp
│   │           ├── order
│   │           ├── order.jsp
│   │           └── warning.jsp
│   ├── sql
│   │   └── Lab3.sql
│   ├── src
│   │   ├── action
│   │   │   └── business
│   │   │       └── OrderListBean.java
│   │   ├── dao
│   │   │   ├── OrderDao.java
│   │   │   ├── OrderDaoImpl.java
│   │   │   ├── UserDao.java
│   │   │   └── UserDaoImpl.java
│   │   ├── factory
│   │   │   ├── DaoFactory.java
│   │   │   └── ServiceFactory.java
│   │   ├── filter
│   │   │   └── CharacterEncodingFilter.java
│   │   ├── listener
│   │   │   └── CounterListener.java
│   │   ├── model
│   │   │   ├── Order.java
│   │   │   └── User.java
│   │   ├── service
│   │   │   ├── OrderService.java
│   │   │   ├── OrderServiceImpl.java
│   │   │   ├── UserService.java
│   │   │   └── UserServiceImpl.java
│   │   ├── servlet
│   │   │   ├── Login.java
│   │   │   ├── Reset.java
│   │   │   └── ShowOrderServlet.java
│   │   ├── tag
│   │   │   ├── CheckSessionHandler.java
│   │   │   ├── CounterHandler.java
│   │   │   └── OrderInfoHandler.java
│   │   └── util
│   │       ├── DatabaseConnection.java
│   │       └── FileHelper.java
│   └── web
│       ├── META-INF
│       │   ├── MANIFEST.MF
│       │   └── context.xml
│       ├── WEB-INF
│       │   ├── classes
│       │   │   ├── action
│       │   │   │   └── business
│       │   │   │       └── OrderListBean.class
│       │   │   ├── dao
│       │   │   │   ├── OrderDao.class
│       │   │   │   ├── OrderDaoImpl.class
│       │   │   │   ├── UserDao.class
│       │   │   │   └── UserDaoImpl.class
│       │   │   ├── factory
│       │   │   │   ├── DaoFactory.class
│       │   │   │   └── ServiceFactory.class
│       │   │   ├── filter
│       │   │   │   └── CharacterEncodingFilter.class
│       │   │   ├── listener
│       │   │   │   └── CounterListener.class
│       │   │   ├── model
│       │   │   │   ├── Order.class
│       │   │   │   └── User.class
│       │   │   ├── service
│       │   │   │   ├── OrderService.class
│       │   │   │   ├── OrderServiceImpl.class
│       │   │   │   ├── UserService.class
│       │   │   │   └── UserServiceImpl.class
│       │   │   ├── servlet
│       │   │   │   ├── Login.class
│       │   │   │   ├── Reset.class
│       │   │   │   └── ShowOrderServlet.class
│       │   │   ├── tag
│       │   │   │   ├── CheckSessionHandler.class
│       │   │   │   ├── CounterHandler.class
│       │   │   │   └── OrderInfoHandler.class
│       │   │   └── util
│       │   │       ├── DatabaseConnection.class
│       │   │       └── FileHelper.class
│       │   ├── lib
│       │   │   ├── jstl-1.2.jar
│       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   ├── servlet-api.jar
│       │   │   └── standard-1.1.2.jar
│       │   ├── tlds
│       │   │   └── songkuixi.tld
│       │   └── web.xml
│       ├── data
│       │   └── counter.txt
│       ├── index.jsp
│       ├── login.jsp
│       ├── order.jsp
│       └── warning.jsp
├── Lab4
│   ├── Client
│   │   ├── Client.iml
│   │   ├── src
│   │   │   ├── action
│   │   │   │   └── business
│   │   │   │       └── OrderListBean.java
│   │   │   ├── factory
│   │   │   │   └── EJBFactory.java
│   │   │   ├── filter
│   │   │   │   └── CharacterEncodingFilter.java
│   │   │   ├── jboss-ejb-client.properties
│   │   │   ├── listener
│   │   │   │   └── CounterListener.java
│   │   │   ├── model
│   │   │   │   ├── Order.java
│   │   │   │   └── User.java
│   │   │   ├── service
│   │   │   │   ├── OrderService.java
│   │   │   │   └── UserService.java
│   │   │   ├── servlet
│   │   │   │   ├── Login.java
│   │   │   │   ├── Reset.java
│   │   │   │   └── ShowOrderServlet.java
│   │   │   ├── tag
│   │   │   │   ├── CheckSessionHandler.java
│   │   │   │   ├── CounterHandler.java
│   │   │   │   └── OrderInfoHandler.java
│   │   │   └── util
│   │   │       └── FileHelper.java
│   │   └── web
│   │       ├── META-INF
│   │       │   ├── MANIFEST.MF
│   │       │   └── context.xml
│   │       ├── WEB-INF
│   │       │   ├── classes
│   │       │   │   ├── action
│   │       │   │   │   └── business
│   │       │   │   │       └── OrderListBean.class
│   │       │   │   ├── factory
│   │       │   │   │   └── EJBFactory.class
│   │       │   │   ├── filter
│   │       │   │   │   └── CharacterEncodingFilter.class
│   │       │   │   ├── jboss-ejb-client.properties
│   │       │   │   ├── listener
│   │       │   │   │   └── CounterListener.class
│   │       │   │   ├── model
│   │       │   │   │   ├── Order.class
│   │       │   │   │   └── User.class
│   │       │   │   ├── service
│   │       │   │   │   ├── OrderService.class
│   │       │   │   │   └── UserService.class
│   │       │   │   ├── servlet
│   │       │   │   │   ├── Login.class
│   │       │   │   │   ├── Reset.class
│   │       │   │   │   └── ShowOrderServlet.class
│   │       │   │   ├── tag
│   │       │   │   │   ├── CheckSessionHandler.class
│   │       │   │   │   ├── CounterHandler.class
│   │       │   │   │   └── OrderInfoHandler.class
│   │       │   │   └── util
│   │       │   │       └── FileHelper.class
│   │       │   ├── lib
│   │       │   │   ├── jboss-client.jar
│   │       │   │   ├── jstl-1.2.jar
│   │       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │       │   │   ├── servlet-api.jar
│   │       │   │   └── standard-1.1.2.jar
│   │       │   ├── tlds
│   │       │   │   └── songkuixi.tld
│   │       │   └── web.xml
│   │       ├── data
│   │       │   └── counter.txt
│   │       ├── index.jsp
│   │       ├── login.jsp
│   │       ├── order.jsp
│   │       └── warning.jsp
│   ├── Lab4.iml
│   ├── Server
│   │   ├── META-INF
│   │   │   └── ejb-jar.xml
│   │   ├── Server.iml
│   │   ├── lib
│   │   │   └── javax.ejb-api.jar
│   │   └── src
│   │       ├── dao
│   │       │   ├── OrderDao.java
│   │       │   ├── OrderDaoImpl.java
│   │       │   ├── UserDao.java
│   │       │   └── UserDaoImpl.java
│   │       ├── factory
│   │       │   ├── DaoFactory.java
│   │       │   └── ServiceFactory.java
│   │       ├── model
│   │       │   ├── Order.java
│   │       │   └── User.java
│   │       ├── service
│   │       │   ├── OrderService.java
│   │       │   ├── OrderServiceBean.java
│   │       │   ├── UserService.java
│   │       │   └── UserServiceBean.java
│   │       └── util
│   │           └── DatabaseConnection.java
│   └── out
│       ├── artifacts
│       │   ├── Client_war_exploded
│       │   │   ├── META-INF
│       │   │   │   ├── MANIFEST.MF
│       │   │   │   └── context.xml
│       │   │   ├── WEB-INF
│       │   │   │   ├── classes
│       │   │   │   │   ├── action
│       │   │   │   │   │   └── business
│       │   │   │   │   │       └── OrderListBean.class
│       │   │   │   │   ├── bean
│       │   │   │   │   │   └── OrderListBean.class
│       │   │   │   │   ├── factory
│       │   │   │   │   │   └── EJBFactory.class
│       │   │   │   │   ├── filter
│       │   │   │   │   │   └── CharacterEncodingFilter.class
│       │   │   │   │   ├── jboss-ejb-client.properties
│       │   │   │   │   ├── listener
│       │   │   │   │   │   └── CounterListener.class
│       │   │   │   │   ├── model
│       │   │   │   │   │   ├── Order.class
│       │   │   │   │   │   └── User.class
│       │   │   │   │   ├── service
│       │   │   │   │   │   ├── OrderService.class
│       │   │   │   │   │   └── UserService.class
│       │   │   │   │   ├── servlet
│       │   │   │   │   │   ├── Login.class
│       │   │   │   │   │   ├── Reset.class
│       │   │   │   │   │   └── ShowOrderServlet.class
│       │   │   │   │   ├── tag
│       │   │   │   │   │   ├── CheckSessionHandler.class
│       │   │   │   │   │   ├── CounterHandler.class
│       │   │   │   │   │   └── OrderInfoHandler.class
│       │   │   │   │   └── util
│       │   │   │   │       └── FileHelper.class
│       │   │   │   ├── lib
│       │   │   │   │   ├── jboss-client.jar
│       │   │   │   │   ├── jstl-1.2.jar
│       │   │   │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   │   │   ├── servlet-api.jar
│       │   │   │   │   └── standard-1.1.2.jar
│       │   │   │   ├── tlds
│       │   │   │   │   └── songkuixi.tld
│       │   │   │   └── web.xml
│       │   │   ├── data
│       │   │   │   └── counter.txt
│       │   │   ├── index.jsp
│       │   │   ├── login.jsp
│       │   │   ├── order.jsp
│       │   │   └── warning.jsp
│       │   └── Server_ejb_exploded.rar
│       │       └── Server_ejb\ exploded.jar
│       └── production
│           └── Server
│               ├── dao
│               │   ├── OrderDao.class
│               │   ├── OrderDaoImpl.class
│               │   ├── UserDao.class
│               │   └── UserDaoImpl.class
│               ├── factory
│               │   ├── DaoFactory.class
│               │   └── ServiceFactory.class
│               ├── model
│               │   ├── Order.class
│               │   └── User.class
│               ├── service
│               │   ├── OrderService.class
│               │   ├── OrderServiceBean.class
│               │   ├── UserService.class
│               │   └── UserServiceBean.class
│               └── util
│                   └── DatabaseConnection.class
├── Lab5
│   ├── Client
│   │   ├── Client.iml
│   │   ├── src
│   │   │   ├── action
│   │   │   │   └── business
│   │   │   │       └── OrderListBean.java
│   │   │   ├── factory
│   │   │   │   └── EJBFactory.java
│   │   │   ├── filter
│   │   │   │   └── CharacterEncodingFilter.java
│   │   │   ├── jboss-ejb-client.properties
│   │   │   ├── listener
│   │   │   │   └── CounterListener.java
│   │   │   ├── model
│   │   │   │   ├── Order.java
│   │   │   │   └── User.java
│   │   │   ├── service
│   │   │   │   ├── OrderService.java
│   │   │   │   └── UserService.java
│   │   │   ├── servlet
│   │   │   │   ├── Login.java
│   │   │   │   ├── Reset.java
│   │   │   │   └── ShowOrderServlet.java
│   │   │   ├── tag
│   │   │   │   ├── CheckSessionHandler.java
│   │   │   │   ├── CounterHandler.java
│   │   │   │   └── OrderInfoHandler.java
│   │   │   └── util
│   │   │       └── FileHelper.java
│   │   └── web
│   │       ├── META-INF
│   │       │   ├── MANIFEST.MF
│   │       │   └── context.xml
│   │       ├── WEB-INF
│   │       │   ├── classes
│   │       │   │   ├── action
│   │       │   │   │   └── business
│   │       │   │   │       └── OrderListBean.class
│   │       │   │   ├── factory
│   │       │   │   │   └── EJBFactory.class
│   │       │   │   ├── filter
│   │       │   │   │   └── CharacterEncodingFilter.class
│   │       │   │   ├── jboss-ejb-client.properties
│   │       │   │   ├── listener
│   │       │   │   │   └── CounterListener.class
│   │       │   │   ├── model
│   │       │   │   │   ├── Order.class
│   │       │   │   │   └── User.class
│   │       │   │   ├── service
│   │       │   │   │   ├── OrderService.class
│   │       │   │   │   └── UserService.class
│   │       │   │   ├── servlet
│   │       │   │   │   ├── Login.class
│   │       │   │   │   ├── Reset.class
│   │       │   │   │   └── ShowOrderServlet.class
│   │       │   │   ├── tag
│   │       │   │   │   ├── CheckSessionHandler.class
│   │       │   │   │   ├── CounterHandler.class
│   │       │   │   │   └── OrderInfoHandler.class
│   │       │   │   └── util
│   │       │   │       └── FileHelper.class
│   │       │   ├── lib
│   │       │   │   ├── jboss-client.jar
│   │       │   │   ├── jstl-1.2.jar
│   │       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │       │   │   ├── servlet-api.jar
│   │       │   │   └── standard-1.1.2.jar
│   │       │   ├── tlds
│   │       │   │   └── songkuixi.tld
│   │       │   └── web.xml
│   │       ├── data
│   │       │   └── counter.txt
│   │       ├── index.jsp
│   │       ├── login.jsp
│   │       ├── order.jsp
│   │       └── warning.jsp
│   ├── Lab5.iml
│   ├── Server
│   │   ├── META-INF
│   │   │   └── ejb-jar.xml
│   │   ├── Server.iml
│   │   ├── lib
│   │   │   ├── javax.ejb-api.jar
│   │   │   └── javax.persistence.jar
│   │   └── src
│   │       ├── META-INF
│   │       │   └── persistence.xml
│   │       ├── dao
│   │       │   ├── OrderDao.java
│   │       │   ├── OrderDaoImpl.java
│   │       │   ├── UserDao.java
│   │       │   └── UserDaoImpl.java
│   │       ├── factory
│   │       │   └── DaoFactory.java
│   │       ├── model
│   │       │   ├── Order.java
│   │       │   └── User.java
│   │       └── service
│   │           ├── OrderService.java
│   │           ├── OrderServiceBean.java
│   │           ├── UserService.java
│   │           └── UserServiceBean.java
│   └── out
│       ├── artifacts
│       │   ├── Client_war_exploded
│       │   │   ├── META-INF
│       │   │   │   ├── MANIFEST.MF
│       │   │   │   └── context.xml
│       │   │   ├── WEB-INF
│       │   │   │   ├── classes
│       │   │   │   │   ├── action
│       │   │   │   │   │   └── business
│       │   │   │   │   │       └── OrderListBean.class
│       │   │   │   │   ├── bean
│       │   │   │   │   │   └── OrderListBean.class
│       │   │   │   │   ├── factory
│       │   │   │   │   │   └── EJBFactory.class
│       │   │   │   │   ├── filter
│       │   │   │   │   │   └── CharacterEncodingFilter.class
│       │   │   │   │   ├── jboss-ejb-client.properties
│       │   │   │   │   ├── listener
│       │   │   │   │   │   └── CounterListener.class
│       │   │   │   │   ├── model
│       │   │   │   │   │   ├── Order.class
│       │   │   │   │   │   └── User.class
│       │   │   │   │   ├── service
│       │   │   │   │   │   ├── OrderService.class
│       │   │   │   │   │   └── UserService.class
│       │   │   │   │   ├── servlet
│       │   │   │   │   │   ├── Login.class
│       │   │   │   │   │   ├── Reset.class
│       │   │   │   │   │   └── ShowOrderServlet.class
│       │   │   │   │   ├── tag
│       │   │   │   │   │   ├── CheckSessionHandler.class
│       │   │   │   │   │   ├── CounterHandler.class
│       │   │   │   │   │   └── OrderInfoHandler.class
│       │   │   │   │   └── util
│       │   │   │   │       └── FileHelper.class
│       │   │   │   ├── lib
│       │   │   │   │   ├── jboss-client.jar
│       │   │   │   │   ├── jstl-1.2.jar
│       │   │   │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   │   │   ├── servlet-api.jar
│       │   │   │   │   └── standard-1.1.2.jar
│       │   │   │   ├── tlds
│       │   │   │   │   └── songkuixi.tld
│       │   │   │   └── web.xml
│       │   │   ├── data
│       │   │   │   └── counter.txt
│       │   │   ├── index.jsp
│       │   │   ├── login.jsp
│       │   │   ├── order.jsp
│       │   │   └── warning.jsp
│       │   └── Server_ejb_exploded.rar
│       │       └── Server_ejb\ exploded.jar
│       └── production
│           └── Server
│               ├── META-INF
│               │   └── persistence.xml
│               ├── dao
│               │   ├── OrderDao.class
│               │   ├── OrderDaoImpl.class
│               │   ├── UserDao.class
│               │   └── UserDaoImpl.class
│               ├── factory
│               │   └── DaoFactory.class
│               ├── model
│               │   ├── Order.class
│               │   └── User.class
│               └── service
│                   ├── OrderService.class
│                   ├── OrderServiceBean.class
│                   ├── UserService.class
│                   └── UserServiceBean.class
├── Lab6
│   ├── Lab6.iml
│   ├── lib
│   │   ├── antlr-2.7.7.jar
│   │   ├── classmate-1.3.0.jar
│   │   ├── dom4j-1.6.1.jar
│   │   ├── hibernate-commons-annotations-5.0.1.Final.jar
│   │   ├── hibernate-core-5.2.12.Final.jar
│   │   ├── hibernate-jpa-2.1-api-1.0.0.Final.jar
│   │   ├── jandex-2.0.3.Final.jar
│   │   ├── javassist-3.20.0-GA.jar
│   │   ├── jboss-logging-3.3.0.Final.jar
│   │   └── jboss-transaction-api_1.2_spec-1.0.1.Final.jar
│   ├── out
│   │   └── artifacts
│   │       └── Lab6_war_exploded
│   │           ├── META-INF
│   │           │   ├── MANIFEST.MF
│   │           │   └── context.xml
│   │           ├── WEB-INF
│   │           │   ├── classes
│   │           │   │   ├── Main.class
│   │           │   │   ├── action
│   │           │   │   │   └── business
│   │           │   │   │       └── OrderListBean.class
│   │           │   │   ├── bean
│   │           │   │   ├── dao
│   │           │   │   │   ├── OrderDao.class
│   │           │   │   │   ├── OrderDaoImpl.class
│   │           │   │   │   ├── UserDao.class
│   │           │   │   │   └── UserDaoImpl.class
│   │           │   │   ├── factory
│   │           │   │   │   ├── DaoFactory.class
│   │           │   │   │   └── ServiceFactory.class
│   │           │   │   ├── filter
│   │           │   │   │   └── CharacterEncodingFilter.class
│   │           │   │   ├── hibernate.cfg.xml
│   │           │   │   ├── listener
│   │           │   │   │   └── CounterListener.class
│   │           │   │   ├── model
│   │           │   │   │   ├── Order.class
│   │           │   │   │   └── User.class
│   │           │   │   ├── service
│   │           │   │   │   ├── OrderService.class
│   │           │   │   │   ├── OrderServiceImpl.class
│   │           │   │   │   ├── UserService.class
│   │           │   │   │   └── UserServiceImpl.class
│   │           │   │   ├── servlet
│   │           │   │   │   ├── Login.class
│   │           │   │   │   ├── Reset.class
│   │           │   │   │   └── ShowOrderServlet.class
│   │           │   │   ├── tag
│   │           │   │   │   ├── CheckSessionHandler.class
│   │           │   │   │   ├── CounterHandler.class
│   │           │   │   │   └── OrderInfoHandler.class
│   │           │   │   └── util
│   │           │   │       └── FileHelper.class
│   │           │   ├── lib
│   │           │   │   ├── antlr-2.7.7.jar
│   │           │   │   ├── classmate-1.3.0.jar
│   │           │   │   ├── dom4j-1.6.1.jar
│   │           │   │   ├── hibernate-commons-annotations-5.0.1.Final.jar
│   │           │   │   ├── hibernate-core-5.2.12.Final.jar
│   │           │   │   ├── hibernate-jpa-2.1-api-1.0.0.Final.jar
│   │           │   │   ├── jandex-2.0.3.Final.jar
│   │           │   │   ├── javassist-3.20.0-GA.jar
│   │           │   │   ├── jboss-logging-3.3.0.Final.jar
│   │           │   │   ├── jboss-transaction-api_1.2_spec-1.0.1.Final.jar
│   │           │   │   ├── jstl-1.2.jar
│   │           │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │           │   │   ├── servlet-api.jar
│   │           │   │   └── standard-1.1.2.jar
│   │           │   ├── tlds
│   │           │   │   └── songkuixi.tld
│   │           │   └── web.xml
│   │           ├── data
│   │           │   └── counter.txt
│   │           ├── index.jsp
│   │           ├── login.jsp
│   │           ├── order.jsp
│   │           └── warning.jsp
│   ├── sql
│   │   └── Lab6.sql
│   ├── src
│   │   ├── Main.java
│   │   ├── action
│   │   │   └── business
│   │   │       └── OrderListBean.java
│   │   ├── dao
│   │   │   ├── OrderDao.java
│   │   │   ├── OrderDaoImpl.java
│   │   │   ├── UserDao.java
│   │   │   └── UserDaoImpl.java
│   │   ├── factory
│   │   │   ├── DaoFactory.java
│   │   │   └── ServiceFactory.java
│   │   ├── filter
│   │   │   └── CharacterEncodingFilter.java
│   │   ├── hibernate.cfg.xml
│   │   ├── listener
│   │   │   └── CounterListener.java
│   │   ├── model
│   │   │   ├── Order.java
│   │   │   └── User.java
│   │   ├── service
│   │   │   ├── OrderService.java
│   │   │   ├── OrderServiceImpl.java
│   │   │   ├── UserService.java
│   │   │   └── UserServiceImpl.java
│   │   ├── servlet
│   │   │   ├── Login.java
│   │   │   ├── Reset.java
│   │   │   └── ShowOrderServlet.java
│   │   ├── tag
│   │   │   ├── CheckSessionHandler.java
│   │   │   ├── CounterHandler.java
│   │   │   └── OrderInfoHandler.java
│   │   └── util
│   │       └── FileHelper.java
│   └── web
│       ├── META-INF
│       │   ├── MANIFEST.MF
│       │   └── context.xml
│       ├── WEB-INF
│       │   ├── classes
│       │   │   ├── Main.class
│       │   │   ├── action
│       │   │   │   └── business
│       │   │   │       └── OrderListBean.class
│       │   │   ├── dao
│       │   │   │   ├── OrderDao.class
│       │   │   │   ├── OrderDaoImpl.class
│       │   │   │   ├── UserDao.class
│       │   │   │   └── UserDaoImpl.class
│       │   │   ├── factory
│       │   │   │   ├── DaoFactory.class
│       │   │   │   └── ServiceFactory.class
│       │   │   ├── filter
│       │   │   │   └── CharacterEncodingFilter.class
│       │   │   ├── hibernate.cfg.xml
│       │   │   ├── listener
│       │   │   │   └── CounterListener.class
│       │   │   ├── model
│       │   │   │   ├── Order.class
│       │   │   │   └── User.class
│       │   │   ├── service
│       │   │   │   ├── OrderService.class
│       │   │   │   ├── OrderServiceImpl.class
│       │   │   │   ├── UserService.class
│       │   │   │   └── UserServiceImpl.class
│       │   │   ├── servlet
│       │   │   │   ├── Login.class
│       │   │   │   ├── Reset.class
│       │   │   │   └── ShowOrderServlet.class
│       │   │   ├── tag
│       │   │   │   ├── CheckSessionHandler.class
│       │   │   │   ├── CounterHandler.class
│       │   │   │   └── OrderInfoHandler.class
│       │   │   └── util
│       │   │       └── FileHelper.class
│       │   ├── lib
│       │   │   ├── jstl-1.2.jar
│       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   ├── servlet-api.jar
│       │   │   └── standard-1.1.2.jar
│       │   ├── tlds
│       │   │   └── songkuixi.tld
│       │   └── web.xml
│       ├── data
│       │   └── counter.txt
│       ├── index.jsp
│       ├── login.jsp
│       ├── order.jsp
│       └── warning.jsp
├── Lab7
│   ├── Lab7.iml
│   ├── lib
│   │   ├── antlr-2.7.7.jar
│   │   ├── aspectjweaver-1.8.8.jar
│   │   ├── c3p0-0.9.5.2.jar
│   │   ├── classmate-1.3.0.jar
│   │   ├── commons-logging-1.2.jar
│   │   ├── dom4j-1.6.1.jar
│   │   ├── hibernate-c3p0-5.2.12.Final.jar
│   │   ├── hibernate-commons-annotations-5.0.1.Final.jar
│   │   ├── hibernate-core-5.2.12.Final.jar
│   │   ├── hibernate-jpa-2.1-api-1.0.0.Final.jar
│   │   ├── jandex-2.0.3.Final.jar
│   │   ├── javassist-3.20.0-GA.jar
│   │   ├── jboss-logging-3.3.0.Final.jar
│   │   ├── jboss-transaction-api_1.2_spec-1.0.1.Final.jar
│   │   ├── mchange-commons-java-0.2.11.jar
│   │   ├── mysql-connector-java-6.0.6.jar
│   │   ├── spring-aop-5.0.2.RELEASE.jar
│   │   ├── spring-aspects-5.0.2.RELEASE.jar
│   │   ├── spring-beans-5.0.2.RELEASE.jar
│   │   ├── spring-context-5.0.2.RELEASE.jar
│   │   ├── spring-core-5.0.2.RELEASE.jar
│   │   ├── spring-dao-2.0.8.jar
│   │   ├── spring-expression-5.0.2.RELEASE.jar
│   │   ├── spring-jdbc-5.0.2.RELEASE.jar
│   │   ├── spring-orm-5.0.2.RELEASE.jar
│   │   ├── spring-tx-5.0.2.RELEASE.jar
│   │   └── spring-web-5.0.2.RELEASE.jar
│   ├── out
│   │   └── artifacts
│   │       └── Lab7_war_exploded
│   │           ├── META-INF
│   │           │   ├── MANIFEST.MF
│   │           │   └── context.xml
│   │           ├── WEB-INF
│   │           │   ├── classes
│   │           │   │   ├── action
│   │           │   │   │   └── business
│   │           │   │   │       └── OrderListBean.class
│   │           │   │   ├── applicationContext.xml
│   │           │   │   ├── bean
│   │           │   │   │   └── OrderListBean.class
│   │           │   │   ├── dao
│   │           │   │   │   ├── BaseDao.class
│   │           │   │   │   ├── BaseDaoImpl.class
│   │           │   │   │   ├── OrderDao.class
│   │           │   │   │   ├── OrderDaoImpl.class
│   │           │   │   │   ├── UserDao.class
│   │           │   │   │   └── UserDaoImpl.class
│   │           │   │   ├── factory
│   │           │   │   ├── filter
│   │           │   │   │   └── CharacterEncodingFilter.class
│   │           │   │   ├── hibernate.cfg.xml
│   │           │   │   ├── lab
│   │           │   │   │   ├── bean
│   │           │   │   │   ├── dao
│   │           │   │   │   ├── filter
│   │           │   │   │   ├── listener
│   │           │   │   │   ├── model
│   │           │   │   │   ├── service
│   │           │   │   │   ├── servlet
│   │           │   │   │   ├── tag
│   │           │   │   │   └── util
│   │           │   │   ├── listener
│   │           │   │   │   └── CounterListener.class
│   │           │   │   ├── model
│   │           │   │   │   ├── Order.class
│   │           │   │   │   └── User.class
│   │           │   │   ├── service
│   │           │   │   │   ├── OrderService.class
│   │           │   │   │   ├── OrderServiceImpl.class
│   │           │   │   │   ├── UserService.class
│   │           │   │   │   └── UserServiceImpl.class
│   │           │   │   ├── servlet
│   │           │   │   │   ├── Login.class
│   │           │   │   │   ├── Reset.class
│   │           │   │   │   └── ShowOrderServlet.class
│   │           │   │   ├── tag
│   │           │   │   │   ├── CheckSessionHandler.class
│   │           │   │   │   ├── CounterHandler.class
│   │           │   │   │   └── OrderInfoHandler.class
│   │           │   │   └── util
│   │           │   │       └── FileHelper.class
│   │           │   ├── lib
│   │           │   │   ├── antlr-2.7.7.jar
│   │           │   │   ├── aspectjweaver-1.8.8.jar
│   │           │   │   ├── c3p0-0.9.5.2.jar
│   │           │   │   ├── classmate-1.3.0.jar
│   │           │   │   ├── commons-logging-1.2.jar
│   │           │   │   ├── dom4j-1.6.1.jar
│   │           │   │   ├── hibernate-c3p0-5.2.12.Final.jar
│   │           │   │   ├── hibernate-commons-annotations-5.0.1.Final.jar
│   │           │   │   ├── hibernate-core-5.2.12.Final.jar
│   │           │   │   ├── hibernate-jpa-2.1-api-1.0.0.Final.jar
│   │           │   │   ├── jandex-2.0.3.Final.jar
│   │           │   │   ├── javassist-3.20.0-GA.jar
│   │           │   │   ├── jboss-logging-3.3.0.Final.jar
│   │           │   │   ├── jboss-transaction-api_1.2_spec-1.0.1.Final.jar
│   │           │   │   ├── jstl-1.2.jar
│   │           │   │   ├── mchange-commons-java-0.2.11.jar
│   │           │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │           │   │   ├── mysql-connector-java-6.0.6.jar
│   │           │   │   ├── servlet-api.jar
│   │           │   │   ├── spring-aop-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-aspects-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-beans-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-context-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-core-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-dao-2.0.8.jar
│   │           │   │   ├── spring-expression-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-jdbc-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-orm-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-tx-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-web-5.0.2.RELEASE.jar
│   │           │   │   └── standard-1.1.2.jar
│   │           │   ├── tlds
│   │           │   │   └── songkuixi.tld
│   │           │   └── web.xml
│   │           ├── data
│   │           │   └── counter.txt
│   │           ├── index.jsp
│   │           ├── login.jsp
│   │           ├── order.jsp
│   │           └── warning.jsp
│   ├── sql
│   │   └── Lab7.sql
│   ├── src
│   │   ├── action
│   │   │   └── business
│   │   │       └── OrderListBean.java
│   │   ├── applicationContext.xml
│   │   ├── dao
│   │   │   ├── BaseDao.java
│   │   │   ├── BaseDaoImpl.java
│   │   │   ├── OrderDao.java
│   │   │   ├── OrderDaoImpl.java
│   │   │   ├── UserDao.java
│   │   │   └── UserDaoImpl.java
│   │   ├── filter
│   │   │   └── CharacterEncodingFilter.java
│   │   ├── listener
│   │   │   └── CounterListener.java
│   │   ├── model
│   │   │   ├── Order.java
│   │   │   └── User.java
│   │   ├── service
│   │   │   ├── OrderService.java
│   │   │   ├── OrderServiceImpl.java
│   │   │   ├── UserService.java
│   │   │   └── UserServiceImpl.java
│   │   ├── servlet
│   │   │   ├── Login.java
│   │   │   ├── Reset.java
│   │   │   └── ShowOrderServlet.java
│   │   ├── tag
│   │   │   ├── CheckSessionHandler.java
│   │   │   ├── CounterHandler.java
│   │   │   └── OrderInfoHandler.java
│   │   └── util
│   │       └── FileHelper.java
│   └── web
│       ├── META-INF
│       │   ├── MANIFEST.MF
│       │   └── context.xml
│       ├── WEB-INF
│       │   ├── classes
│       │   │   ├── action
│       │   │   │   └── business
│       │   │   │       └── OrderListBean.class
│       │   │   ├── applicationContext.xml
│       │   │   ├── bean
│       │   │   │   └── OrderListBean.class
│       │   │   ├── dao
│       │   │   │   ├── BaseDao.class
│       │   │   │   ├── BaseDaoImpl.class
│       │   │   │   ├── OrderDao.class
│       │   │   │   ├── OrderDaoImpl.class
│       │   │   │   ├── UserDao.class
│       │   │   │   └── UserDaoImpl.class
│       │   │   ├── filter
│       │   │   │   └── CharacterEncodingFilter.class
│       │   │   ├── hibernate.cfg.xml
│       │   │   ├── listener
│       │   │   │   └── CounterListener.class
│       │   │   ├── model
│       │   │   │   ├── Order.class
│       │   │   │   └── User.class
│       │   │   ├── service
│       │   │   │   ├── OrderService.class
│       │   │   │   ├── OrderServiceImpl.class
│       │   │   │   ├── UserService.class
│       │   │   │   └── UserServiceImpl.class
│       │   │   ├── servlet
│       │   │   │   ├── Login.class
│       │   │   │   ├── Reset.class
│       │   │   │   └── ShowOrderServlet.class
│       │   │   ├── tag
│       │   │   │   ├── CheckSessionHandler.class
│       │   │   │   ├── CounterHandler.class
│       │   │   │   └── OrderInfoHandler.class
│       │   │   └── util
│       │   │       └── FileHelper.class
│       │   ├── lib
│       │   │   ├── jstl-1.2.jar
│       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   ├── servlet-api.jar
│       │   │   └── standard-1.1.2.jar
│       │   ├── tlds
│       │   │   └── songkuixi.tld
│       │   └── web.xml
│       ├── data
│       │   └── counter.txt
│       ├── index.jsp
│       ├── login.jsp
│       ├── order.jsp
│       └── warning.jsp
├── Lab8
│   ├── Lab8.iml
│   ├── lib
│   │   ├── antlr-2.7.7.jar
│   │   ├── aopalliance-1.0.jar
│   │   ├── asm-5.2.jar
│   │   ├── asm-commons-5.2.jar
│   │   ├── asm-tree-5.2.jar
│   │   ├── aspectjweaver-1.8.8.jar
│   │   ├── c3p0-0.9.5.2.jar
│   │   ├── classmate-1.3.0.jar
│   │   ├── commons-fileupload-1.3.3.jar
│   │   ├── commons-io-2.5.jar
│   │   ├── commons-lang3-3.6.jar
│   │   ├── commons-logging-1.2.jar
│   │   ├── dom4j-1.6.1.jar
│   │   ├── freemarker-2.3.23.jar
│   │   ├── hibernate-c3p0-5.2.12.Final.jar
│   │   ├── hibernate-commons-annotations-5.0.1.Final.jar
│   │   ├── hibernate-core-5.2.12.Final.jar
│   │   ├── hibernate-jpa-2.1-api-1.0.0.Final.jar
│   │   ├── jandex-2.0.3.Final.jar
│   │   ├── javassist-3.20.0-GA.jar
│   │   ├── jboss-logging-3.3.0.Final.jar
│   │   ├── jboss-transaction-api_1.2_spec-1.0.1.Final.jar
│   │   ├── log4j-api-2.9.1.jar
│   │   ├── mchange-commons-java-0.2.11.jar
│   │   ├── mysql-connector-java-6.0.6.jar
│   │   ├── ognl-3.1.15.jar
│   │   ├── spring-aop-5.0.2.RELEASE.jar
│   │   ├── spring-aspects-5.0.2.RELEASE.jar
│   │   ├── spring-beans-5.0.2.RELEASE.jar
│   │   ├── spring-context-5.0.2.RELEASE.jar
│   │   ├── spring-core-5.0.2.RELEASE.jar
│   │   ├── spring-dao-2.0.8.jar
│   │   ├── spring-expression-5.0.2.RELEASE.jar
│   │   ├── spring-jdbc-5.0.2.RELEASE.jar
│   │   ├── spring-orm-5.0.2.RELEASE.jar
│   │   ├── spring-tx-5.0.2.RELEASE.jar
│   │   ├── spring-web-5.0.2.RELEASE.jar
│   │   ├── spring-webmvc-5.0.2.RELEASE.jar
│   │   ├── struts2-core-2.5.14.1.jar
│   │   └── struts2-spring-plugin-2.5.14.1.jar
│   ├── out
│   │   └── artifacts
│   │       ├── Lab7_war_exploded
│   │       │   ├── META-INF
│   │       │   │   ├── MANIFEST.MF
│   │       │   │   └── context.xml
│   │       │   ├── WEB-INF
│   │       │   │   ├── classes
│   │       │   │   │   ├── action
│   │       │   │   │   │   └── business
│   │       │   │   │   │       └── OrderListBean.class
│   │       │   │   │   ├── applicationContext.xml
│   │       │   │   │   ├── bean
│   │       │   │   │   │   └── OrderListBean.class
│   │       │   │   │   ├── dao
│   │       │   │   │   │   ├── BaseDao.class
│   │       │   │   │   │   ├── BaseDaoImpl.class
│   │       │   │   │   │   ├── OrderDao.class
│   │       │   │   │   │   ├── OrderDaoImpl.class
│   │       │   │   │   │   ├── UserDao.class
│   │       │   │   │   │   └── UserDaoImpl.class
│   │       │   │   │   ├── factory
│   │       │   │   │   ├── filter
│   │       │   │   │   │   └── CharacterEncodingFilter.class
│   │       │   │   │   ├── hibernate.cfg.xml
│   │       │   │   │   ├── lab
│   │       │   │   │   │   ├── bean
│   │       │   │   │   │   ├── dao
│   │       │   │   │   │   ├── filter
│   │       │   │   │   │   ├── listener
│   │       │   │   │   │   ├── model
│   │       │   │   │   │   ├── service
│   │       │   │   │   │   ├── servlet
│   │       │   │   │   │   ├── tag
│   │       │   │   │   │   └── util
│   │       │   │   │   ├── listener
│   │       │   │   │   │   └── CounterListener.class
│   │       │   │   │   ├── model
│   │       │   │   │   │   ├── Order.class
│   │       │   │   │   │   └── User.class
│   │       │   │   │   ├── service
│   │       │   │   │   │   ├── OrderService.class
│   │       │   │   │   │   ├── OrderServiceImpl.class
│   │       │   │   │   │   ├── UserService.class
│   │       │   │   │   │   └── UserServiceImpl.class
│   │       │   │   │   ├── servlet
│   │       │   │   │   │   ├── Login.class
│   │       │   │   │   │   ├── Reset.class
│   │       │   │   │   │   └── ShowOrderServlet.class
│   │       │   │   │   ├── tag
│   │       │   │   │   │   ├── CheckSessionHandler.class
│   │       │   │   │   │   ├── CounterHandler.class
│   │       │   │   │   │   └── OrderInfoHandler.class
│   │       │   │   │   └── util
│   │       │   │   │       └── FileHelper.class
│   │       │   │   ├── lib
│   │       │   │   │   ├── antlr-2.7.7.jar
│   │       │   │   │   ├── aspectjweaver-1.8.8.jar
│   │       │   │   │   ├── c3p0-0.9.5.2.jar
│   │       │   │   │   ├── classmate-1.3.0.jar
│   │       │   │   │   ├── commons-logging-1.2.jar
│   │       │   │   │   ├── dom4j-1.6.1.jar
│   │       │   │   │   ├── hibernate-c3p0-5.2.12.Final.jar
│   │       │   │   │   ├── hibernate-commons-annotations-5.0.1.Final.jar
│   │       │   │   │   ├── hibernate-core-5.2.12.Final.jar
│   │       │   │   │   ├── hibernate-jpa-2.1-api-1.0.0.Final.jar
│   │       │   │   │   ├── jandex-2.0.3.Final.jar
│   │       │   │   │   ├── javassist-3.20.0-GA.jar
│   │       │   │   │   ├── jboss-logging-3.3.0.Final.jar
│   │       │   │   │   ├── jboss-transaction-api_1.2_spec-1.0.1.Final.jar
│   │       │   │   │   ├── jstl-1.2.jar
│   │       │   │   │   ├── mchange-commons-java-0.2.11.jar
│   │       │   │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │       │   │   │   ├── mysql-connector-java-6.0.6.jar
│   │       │   │   │   ├── servlet-api.jar
│   │       │   │   │   ├── spring-aop-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-aspects-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-beans-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-context-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-core-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-dao-2.0.8.jar
│   │       │   │   │   ├── spring-expression-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-jdbc-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-orm-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-tx-5.0.2.RELEASE.jar
│   │       │   │   │   ├── spring-web-5.0.2.RELEASE.jar
│   │       │   │   │   └── standard-1.1.2.jar
│   │       │   │   ├── tlds
│   │       │   │   │   └── songkuixi.tld
│   │       │   │   └── web.xml
│   │       │   ├── data
│   │       │   │   └── counter.txt
│   │       │   ├── index.jsp
│   │       │   ├── login.jsp
│   │       │   ├── order.jsp
│   │       │   └── warning.jsp
│   │       └── Lab8_war_exploded
│   │           ├── META-INF
│   │           │   ├── MANIFEST.MF
│   │           │   └── context.xml
│   │           ├── WEB-INF
│   │           │   ├── classes
│   │           │   │   ├── action
│   │           │   │   │   ├── BaseAction.class
│   │           │   │   │   ├── LoginAction.class
│   │           │   │   │   ├── ShowAction.class
│   │           │   │   │   └── business
│   │           │   │   │       └── OrderListBean.class
│   │           │   │   ├── applicationContext.xml
│   │           │   │   ├── dao
│   │           │   │   │   ├── BaseDao.class
│   │           │   │   │   ├── BaseDaoImpl.class
│   │           │   │   │   ├── OrderDao.class
│   │           │   │   │   ├── OrderDaoImpl.class
│   │           │   │   │   ├── UserDao.class
│   │           │   │   │   └── UserDaoImpl.class
│   │           │   │   ├── filter
│   │           │   │   │   └── CharacterEncodingFilter.class
│   │           │   │   ├── listener
│   │           │   │   │   └── CounterListener.class
│   │           │   │   ├── model
│   │           │   │   │   ├── Order.class
│   │           │   │   │   └── User.class
│   │           │   │   ├── service
│   │           │   │   │   ├── OrderService.class
│   │           │   │   │   ├── OrderServiceImpl.class
│   │           │   │   │   ├── UserService.class
│   │           │   │   │   └── UserServiceImpl.class
│   │           │   │   ├── servlet
│   │           │   │   │   └── Reset.class
│   │           │   │   ├── struts.xml
│   │           │   │   ├── tag
│   │           │   │   │   ├── CheckSessionHandler.class
│   │           │   │   │   ├── CounterHandler.class
│   │           │   │   │   └── OrderInfoHandler.class
│   │           │   │   └── util
│   │           │   │       └── FileHelper.class
│   │           │   ├── lib
│   │           │   │   ├── antlr-2.7.7.jar
│   │           │   │   ├── aopalliance-1.0.jar
│   │           │   │   ├── asm-5.2.jar
│   │           │   │   ├── asm-commons-5.2.jar
│   │           │   │   ├── asm-tree-5.2.jar
│   │           │   │   ├── aspectjweaver-1.8.8.jar
│   │           │   │   ├── c3p0-0.9.5.2.jar
│   │           │   │   ├── classmate-1.3.0.jar
│   │           │   │   ├── commons-fileupload-1.3.3.jar
│   │           │   │   ├── commons-io-2.5.jar
│   │           │   │   ├── commons-lang3-3.6.jar
│   │           │   │   ├── commons-logging-1.2.jar
│   │           │   │   ├── dom4j-1.6.1.jar
│   │           │   │   ├── freemarker-2.3.23.jar
│   │           │   │   ├── hibernate-c3p0-5.2.12.Final.jar
│   │           │   │   ├── hibernate-commons-annotations-5.0.1.Final.jar
│   │           │   │   ├── hibernate-core-5.2.12.Final.jar
│   │           │   │   ├── hibernate-jpa-2.1-api-1.0.0.Final.jar
│   │           │   │   ├── jandex-2.0.3.Final.jar
│   │           │   │   ├── javassist-3.20.0-GA.jar
│   │           │   │   ├── jboss-logging-3.3.0.Final.jar
│   │           │   │   ├── jboss-transaction-api_1.2_spec-1.0.1.Final.jar
│   │           │   │   ├── jstl-1.2.jar
│   │           │   │   ├── log4j-api-2.9.1.jar
│   │           │   │   ├── mchange-commons-java-0.2.11.jar
│   │           │   │   ├── mysql-connector-java-5.1.45-bin.jar
│   │           │   │   ├── mysql-connector-java-6.0.6.jar
│   │           │   │   ├── ognl-3.1.15.jar
│   │           │   │   ├── servlet-api.jar
│   │           │   │   ├── spring-aop-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-aspects-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-beans-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-context-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-core-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-dao-2.0.8.jar
│   │           │   │   ├── spring-expression-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-jdbc-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-orm-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-tx-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-web-5.0.2.RELEASE.jar
│   │           │   │   ├── spring-webmvc-5.0.2.RELEASE.jar
│   │           │   │   ├── standard-1.1.2.jar
│   │           │   │   ├── struts2-core-2.5.14.1.jar
│   │           │   │   └── struts2-spring-plugin-2.5.14.1.jar
│   │           │   ├── tlds
│   │           │   │   └── songkuixi.tld
│   │           │   └── web.xml
│   │           ├── data
│   │           │   └── counter.txt
│   │           ├── index.jsp
│   │           ├── login.jsp
│   │           ├── order.jsp
│   │           └── warning.jsp
│   ├── sql
│   │   └── Lab8.sql
│   ├── src
│   │   ├── action
│   │   │   ├── BaseAction.java
│   │   │   ├── LoginAction.java
│   │   │   ├── ShowAction.java
│   │   │   └── business
│   │   │       └── OrderListBean.java
│   │   ├── applicationContext.xml
│   │   ├── dao
│   │   │   ├── BaseDao.java
│   │   │   ├── BaseDaoImpl.java
│   │   │   ├── OrderDao.java
│   │   │   ├── OrderDaoImpl.java
│   │   │   ├── UserDao.java
│   │   │   └── UserDaoImpl.java
│   │   ├── filter
│   │   │   └── CharacterEncodingFilter.java
│   │   ├── listener
│   │   │   └── CounterListener.java
│   │   ├── model
│   │   │   ├── Order.java
│   │   │   └── User.java
│   │   ├── service
│   │   │   ├── OrderService.java
│   │   │   ├── OrderServiceImpl.java
│   │   │   ├── UserService.java
│   │   │   └── UserServiceImpl.java
│   │   ├── servlet
│   │   │   └── Reset.java
│   │   ├── struts.xml
│   │   ├── tag
│   │   │   ├── CheckSessionHandler.java
│   │   │   ├── CounterHandler.java
│   │   │   └── OrderInfoHandler.java
│   │   └── util
│   │       └── FileHelper.java
│   └── web
│       ├── META-INF
│       │   ├── MANIFEST.MF
│       │   └── context.xml
│       ├── WEB-INF
│       │   ├── classes
│       │   │   ├── action
│       │   │   │   ├── BaseAction.class
│       │   │   │   ├── LoginAction.class
│       │   │   │   ├── ShowAction.class
│       │   │   │   └── business
│       │   │   │       └── OrderListBean.class
│       │   │   ├── applicationContext.xml
│       │   │   ├── dao
│       │   │   │   ├── BaseDao.class
│       │   │   │   ├── BaseDaoImpl.class
│       │   │   │   ├── OrderDao.class
│       │   │   │   ├── OrderDaoImpl.class
│       │   │   │   ├── UserDao.class
│       │   │   │   └── UserDaoImpl.class
│       │   │   ├── filter
│       │   │   │   └── CharacterEncodingFilter.class
│       │   │   ├── listener
│       │   │   │   └── CounterListener.class
│       │   │   ├── model
│       │   │   │   ├── Order.class
│       │   │   │   └── User.class
│       │   │   ├── service
│       │   │   │   ├── OrderService.class
│       │   │   │   ├── OrderServiceImpl.class
│       │   │   │   ├── UserService.class
│       │   │   │   └── UserServiceImpl.class
│       │   │   ├── servlet
│       │   │   │   └── Reset.class
│       │   │   ├── struts.xml
│       │   │   ├── tag
│       │   │   │   ├── CheckSessionHandler.class
│       │   │   │   ├── CounterHandler.class
│       │   │   │   └── OrderInfoHandler.class
│       │   │   └── util
│       │   │       └── FileHelper.class
│       │   ├── lib
│       │   │   ├── jstl-1.2.jar
│       │   │   ├── mysql-connector-java-5.1.45-bin.jar
│       │   │   ├── servlet-api.jar
│       │   │   └── standard-1.1.2.jar
│       │   ├── tlds
│       │   │   └── songkuixi.tld
│       │   └── web.xml
│       ├── data
│       │   └── counter.txt
│       ├── index.jsp
│       ├── login.jsp
│       ├── order.jsp
│       └── warning.jsp
└── README.md
```


