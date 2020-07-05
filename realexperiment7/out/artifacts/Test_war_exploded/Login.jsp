<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学生信息管理系统登录</title>
    <script type="text/javascript">
    </script>
  </head>
  <body>
  <h1 align="center">学生信息管理系统登录</h1>
    <form action="LoginServlet" method="post">
      <table align="center">
        <tr>
          <td>账&nbsp&nbsp&nbsp号：</td>
          <td><input type="text" name="username" id="username"/></td>
        </tr>
        <tr>
          <td>密&nbsp&nbsp&nbsp码：</td>
          <td><input type="password" name="password" id="password"/></td>
        </tr>
        <tr align="center">
          <td><input type="radio" checked="checked" id="student" value="${0}" name="role">普通成员</td>
          <td><input type="radio" id="manger" value="${1}" name="role">管理员</td>
        </tr>
        <tr align="center">
          <td><input type="submit" value="登录"/></td>
          <td><input type="reset" value="重置" /></td>
        </tr>
      </table>
    </form>
  <%

  %>
  </body>
</html>
