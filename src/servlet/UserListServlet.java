package servlet;

import org.dal.Author;
import org.model.AuthorInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询
        Author author = new Author();
        List<AuthorInfo> list = null;
        try {
            list = author.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //存进数据域中
        request.setAttribute("author",list);
        //转发
        request.getRequestDispatcher("/admin/admin-index.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
