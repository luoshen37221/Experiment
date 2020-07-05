<%@ page import="Dao.DBUtilsDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>修改页面</title>
</head>
<body>
<h1 align="center">修改学生信息界面</h1>
<form method="post" action="UpdateServlet">
    <input type="hidden" name="id" value="${user.id }">
    <table  align="center" border="1" cellpadding="0" cellspacing="15">
        <tr>
            <td>姓名</td>
            <td><input type="text" name="username" value="${user.username }"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="password" value="${user.password }"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input type="text" name="sex" value="${user.sex }"></td>
        </tr>
        <tr>
            <td>地址</td>
            <td><input type="text" name="address" value="${user.address }"></td>
        </tr>
        <tr>
            <td>电话</td>
            <td><input type="text" name="phone" value="${user.phone }"></td>
        </tr>
        <tr>
            <td>身份</td>
            <td><input type="text" name="role" value="${user.role }"></td>
        </tr>
        <tr>
            <td><input type="submit" value="修改"></td>
        </tr>
    </table>
</form>
</body>
</html>
