<%--
  Created by IntelliJ IDEA.
  User: Kray
  Date: 2018/1/2
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="songkuixi" uri="/WEB-INF/tlds/songkuixi.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Order List</title>
</head>
<body>

<% String currentPage = request.getParameter("page"); %>

<p>Welcome <%= request.getAttribute("username")%>!</p>
<p>订单列表：</p>
<table>
    <tr>
        <td>Id</td>
        <td>Time</td>
        <td>Name</td>
        <td>Count</td>
        <td>Price</td>
        <td>Out of Stock</td>
    </tr>
    <songkuixi:orderInfo/>
</table>

<songkuixi:pageNumber totalNumber="${pageContext.request.getAttribute('totalNumber')}"/>

<form method="get" action="${pageContext.request.contextPath}/Login">
    <input type="submit" name="Logout" value="Logout">
</form>

<songkuixi:counterNumber/>

</body>
</html>
