<%--
  Created by IntelliJ IDEA.
  User: Kray
  Date: 2018/1/1
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="songkuixi" uri="/WEB-INF/tlds/songkuixi.tld" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<%
    String login;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("LoginCookie")) {
                login = cookie.getValue();
                request.setAttribute("username", login);
                break;
            }
        }
    }
%>

<form method="post" action="${pageContext.request.contextPath}/ShowOrderServlet?page=1">
    login: <input type='text' name='username' value="${pageContext.request.getAttribute("username")}">
    password: <input type='password' name='password' value=''>
    <input type='submit' name='Submit' value='Submit'>
</form>

<songkuixi:counterNumber/>

</body>
</html>