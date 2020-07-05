<%@ page import="Dao.DBUtilsDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    HttpSession session1 =request.getSession();
    String username = (String) session1.getAttribute("username");
    String password = (String) session1.getAttribute("password");
    String role = (String) session1.getAttribute("role");
    DBUtilsDao dao = new DBUtilsDao();
    try {
        User user = dao.loginfind(username,password,role);
        if(user.equals(null)){}
        //检测是否直接访问
    } catch (SQLException |NullPointerException a) {
        out.println("<html>");
        out.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head>");
        out.println("<script> type=\"text/javascript\"");
        out.println("alert('禁止直接访问！')");
        out.println("window.location.href='Login.jsp'");
        out.println("</script>");
        out.println("</html>");
        a.printStackTrace();
    }
%>
<html>
<head>
    <title>学生信息管理系统(普通成员)</title>
</head>
<body>
<h1 align="center">学生信息管理系统(普通成员)</h1>
<form action="#" method="post">
    <p align="center">
        <a href="LogoutServlet">返回登录界面</a>
    </p>
    <table  align="center" cellpadding="10" cellspacing="0" border="1">
        <tr>
            <td align="center">
                <strong>学号</strong>
            </td>
            <td align="center">
                <strong>姓名</strong>
            </td>
            <td align="center">
                <strong>性别</strong>
            </td>
            <td align="center">
                <strong>地址</strong>
            </td>
            <td align="center">
                <strong>电话</strong>
            </td>
        </tr>
        <c:forEach items="${list}" var="user">
            <tr>
                <td>
                    <c:out value="${user.id}" />
                </td>
                <td>
                    <c:out value="${user.username}" />
                </td>
                <td>
                    <c:out value="${user.sex}" />
                </td>
                <td>
                    <c:out value="${user.address}" />
                </td>
                <td>
                    <c:out value="${user.phone}" />
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
