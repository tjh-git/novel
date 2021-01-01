package servlet;

import org.common.Utilty;
import org.dal.Admin;
import org.dal.Reader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/readerLoginServlet")
public class ReaderLoginServlet extends HttpServlet {
    public ReaderLoginServlet(){
        super();
    }
    public void init(ServletConfig config) throws ServletException {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username =request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        PrintWriter out = response.getWriter();
        try {
            Reader reader = new Reader();
            if(reader.isExist(username,password)) {
                Utilty.writeCookie(response,"reader",username);
                response.sendRedirect(request.getContextPath()+"/reader/index.jsp");
            }else{
                out.print("<script type ='text/javascript'>");
                out.print("alert('用户名或密码错误');");
                out.print("window.location='index.html';");
                out.print("</script>");
//                response.sendRedirect("index.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
