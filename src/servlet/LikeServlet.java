package servlet;

import org.dal.Novel;
import org.model.NovelInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/likeServlet")
public class LikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String likeId = request.getParameter("likeId");
        int id = Integer.parseInt(likeId);
        Novel novel = new Novel();
        NovelInfo novelInfo = new NovelInfo();
        novelInfo.setId(id);
        novel.updateById(novelInfo);
        System.out.println("id:  "+id);
//        request.getRequestDispatcher("novel/reader/index.jsp").forward(request,response);

        response.sendRedirect(request.getContextPath()+"/reader/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }



}
