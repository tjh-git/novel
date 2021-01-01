package servlet;

import org.dal.Author;
import org.model.AuthorInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/addAuthorServlet")
public class AddAuthorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String authorName = request.getParameter("authorName");
        String authorPassword = request.getParameter("authorPassword");
        String authorEmail = request.getParameter("authorEmail");

        Author author = new Author();
        AuthorInfo authorInfo = new AuthorInfo(authorName,authorPassword,authorEmail);
//        PrintWriter out = response.getWriter();
        try {
            if(!author.isExistAuthorInfo(authorName)){
                author.insert(authorInfo);

                PrintWriter out = response.getWriter();
                System.out.println(authorName+"作者账号注册成功");
                out.print("<script type ='text/javascript'; charset = 'UTF-8'>");
                out.print("alert('Account registration successful');");
                out.println("location.href='index.html'");
                out.print("</script>");
            }else{
                PrintWriter out = response.getWriter();
                System.out.println(authorName+"这种账号已经存在！");
                out.print("<script type ='text/javascript'; charset = 'UTF-8'>");
                out.print("alert('The user name already exists');");
                out.println("location.href='/novel/author/addAuthor.jsp'");
                out.print("</script>");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        response.sendRedirect(request.getContextPath()+"/index.html");
//        request.getRequestDispatcher(request.getContextPath()+"/admin/admin-index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
