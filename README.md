# J2EELab

Lab for J2EE and Middleware Technology.

# 客户订单查询应用

## Lab1：Servlet + Session + JDBC

* 客户登录，根据客户的 ID / PW，查询订单情况，并根据具体情况，返回不同结果（可在 1 个 Servlet 中实现）：
    * 未知的客户 ID：错误页面
    * 正常结果：标准页面
        * 每一项订单：包括多项属性，如时间、名称、数量、价格等
        * 分页显示（选做）
    * 有缺货的订单项：警示页面

## Lab2：Servlet + Session + JDBC + Filter + Listener

* 使用过滤器解决表单中的中文请求后的乱码问题。
* 统计在线人数：
    * 总人数
    * 已登录人数
    * 游客人数
* 思考：用户关闭浏览器，未点击“退出”，如何计算人数？

## Lab3：MVC (DAO + Service + JavaBean + Servlet + JSP + Tag)
## Lab4：DAO:EJB + Service:EJB + JavaBean + Servlet + JSP
## Lab5：DAO:EJB + Service:EJB + JPA + Servlet + JSP + Tag
## Lab6：Hibernate (Hibernate/Entity,DAO + Service + Servlet + JSP)
## Lab7：Spring (Hibernate/Entity + Spring/Service,DAO + Servlet + JSP)
## Lab8：Struts (Hibernate/Entity + Spring/Service,DAO + Struts/JSP)


