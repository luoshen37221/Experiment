package Controller;

import Model.User;
import Dao.DBUtilsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("utf-8");
         response.setCharacterEncoding("utf-8");

         PrintWriter out = response.getWriter();
         String username =request.getParameter("username");
         String password =request.getParameter("password");
         String role =request.getParameter("role");

         DBUtilsDao dao = new DBUtilsDao();

        try {
            User user = dao.loginfind(username,password,role);
            if(user.equals(null)){}
            //检测是否空白以及是否有这个用户
        } catch (SQLException|NullPointerException a) {
            out.println("<html>");
                    out.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head>");
            out.println("<script> type=\"text/javascript\"");
            out.println("alert('请输入正确的账号和密码！')");
            out.println("window.location.href='Login.jsp'");
            out.println("</script>");
            out.println("</html>");
            a.printStackTrace();
        }
        //传给Session,进行是否直接访问的判断
        HttpSession session1 =request.getSession();
        session1.setAttribute("username",request.getParameter("username"));
        session1.setAttribute("password",request.getParameter("password"));
        session1.setAttribute("role",request.getParameter("role"));
        //
        {
            //校验是否为管理员
            if (role.equals("1")) {
                try {
                    User user = dao.loginfind(username, password, role);
                    if (role.equals("1") && !user.equals(null)) {
                        List<User> list = dao.findAll();
                        HttpSession session = request.getSession();
                        session.setAttribute("list", list);
                    }
                } catch (SQLException e) {

                    e.printStackTrace();
                }
                request.getRequestDispatcher("Manager.jsp").forward(request, response);
            } else if (role.equals("0")) {
                //校验是否为普通用户
                try {
                    User user = dao.loginfind(username, password, role);
                    if (role.equals("0") && !user.equals(null)) {
                        List<User> list = dao.findAll();
                        HttpSession session = request.getSession();
                        session.setAttribute("list", list);
                    }
                } catch (SQLException e) {

                    e.printStackTrace();
                }
                request.getRequestDispatcher("Ordinary.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
