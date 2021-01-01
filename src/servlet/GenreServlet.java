package servlet;

import org.dal.Genre;
import org.dal.Reader;
import org.model.GenreInfo;
import org.model.ReaderInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/genreServlet")
public class GenreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询
        Genre genre = new Genre();
        List<GenreInfo> list = null;
        try {
            list = genre.getList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int genreCount = 0;
        try {
            genreCount = genre.getTotalCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("genreCount",genreCount);
        //存进数据域中
        request.setAttribute("genre",list);
        //转发
        request.getRequestDispatcher("/genre/genre-index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
