package Controller;

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

@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out =response.getWriter();
        int id =Integer.parseInt(request.getParameter("id"));
        DBUtilsDao dao = new DBUtilsDao();
        try {
                List list = dao.find1(id);
                HttpSession session = request.getSession();
                //将object变成String
                String list1 = String.valueOf(list);
                if((list1.equals("[]"))){
                    out.println("<html>");
                    out.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head>");
                    out.println("<script> type=\"text/javascript\"");
                    out.println("alert('查询失败！')");
                    out.println("window.location.href='Manager.jsp'");
                    out.println("</script>");
                    out.println("</html>");
                }
                session.setAttribute("list",list);
                out.println("<html>");
                out.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head>");
                out.println("<script> type=\"text/javascript\"");
                out.println("alert('查询成功！')");
                out.println("window.location.href='Manager.jsp'");
                out.println("</script>");
                out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
