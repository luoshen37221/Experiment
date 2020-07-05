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

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        DBUtilsDao dao = new DBUtilsDao();
        PrintWriter out =response.getWriter();
        out.println("<html>");
        out.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head>");
        out.println("<script> type=\"text/javascript\"");
        out.println("alert('删除成功！')");
        out.println("</script>");
        out.println("</html>");
        String id = request.getParameter("id");
        int d = Integer.parseInt(id);

        try {
            boolean flag = dao.delete(d);
            if (flag) {
                List<User> list = dao.findAll();
                HttpSession session = request.getSession();
                session.setAttribute("list", list);
                out.println("<script> type=\"text/javascript\"");
                out.println("window.location.href='Manager.jsp'");
                out.println("</script>");
            } else {
               System.out.println("删除失败");
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
