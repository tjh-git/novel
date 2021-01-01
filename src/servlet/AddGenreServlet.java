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

@WebServlet("/addGenreServlet")
public class AddGenreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String _genre = request.getParameter("genre");
        String _sort = request.getParameter("sort");
        int sort = Integer.parseInt(_sort);

        Genre genre = new Genre();
        GenreInfo genreInfo = new GenreInfo();
        genreInfo.setName(_genre);
        genreInfo.setSort(sort);

        genre.insert(genreInfo);
        response.sendRedirect("findUserByPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
