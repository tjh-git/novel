package servlet;

import org.dal.Author;
import org.model.AuthorInfo;
import org.model.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if(currentPage==null ||"".equals(currentPage)  ){

            currentPage="1";
        }
        if(rows==null ||"".equals(rows)){
            rows="5";
        }

        Map<String,String[]> condition =  request.getParameterMap();




        Author author = new Author();
        PageBean<AuthorInfo> pb = null;
        try {
            pb = author.findUserByPage(currentPage,rows,condition);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        System.out.println(pb);
        request.setAttribute("pb",pb);


        request.getRequestDispatcher("/admin/admin-index.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
