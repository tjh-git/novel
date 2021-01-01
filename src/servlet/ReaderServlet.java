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
import java.sql.SQLException;

import java.util.List;

@WebServlet("/readerServlet")
public class ReaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询
        Reader reader = new Reader();
        List<ReaderInfo> list = null;
        try {
            list = reader.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int readerCount = 0;
        try {
            readerCount = reader.getTotalCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("readerCount",readerCount);
        //存进数据域中
        request.setAttribute("reader",list);
        //转发
        request.getRequestDispatcher("/reader/reader-index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
