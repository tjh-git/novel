package servlet;

import org.dal.Author;
import org.dal.Reader;
import org.model.AuthorInfo;
import org.model.ReaderInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addReaderServlet")
public class AddReaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String readerName = request.getParameter("readerName");
        String readerPassword = request.getParameter("readerPassword");
        String readerEmail = request.getParameter("readerEmail");

        Reader reader = new Reader();
        ReaderInfo readerInfo = new ReaderInfo(readerName,readerPassword,readerEmail);

        try {
            if(!reader.isExistReaderInfo(readerName)){
                reader.insert(readerInfo);

                PrintWriter out = response.getWriter();
                System.out.println(readerName+"读者账号注册成功");
                out.print("<script type ='text/javascript'; charset = 'UTF-8'>");
                out.print("alert('Account registration successful');");
                out.println("location.href='index.html'");
                out.print("</script>");
            }else{
                PrintWriter out = response.getWriter();
                System.out.println(readerName+"读者账号已经存在！");
                out.print("<script type ='text/javascript'; charset = 'UTF-8'>");
                out.print("alert('The user name already exists');");
                out.println("location.href='/novel/reader/reader-register.jsp'");
                out.print("</script>");
//                out.flush();
//                out.close();
//                Thread.sleep(2000);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        PrintWriter out = new PrintWriter();

//        response.sendRedirect(request.getContextPath()+"/index.html");
//        request.getRequestDispatcher("/index.html").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
