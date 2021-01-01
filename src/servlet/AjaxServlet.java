package servlet;

import org.dal.Novel;
import org.model.NovelInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String _id = request.getParameter("id");
        String _num = request.getParameter("num");
        Novel novel = new Novel();
        NovelInfo novelInfo = new NovelInfo();

        novelInfo.setId(Integer.parseInt(_id));
        novelInfo.setVoteNumber(Integer.parseInt(_num));
//        System.out.println("qian: "+_id+_num);
        novel.updateById(novelInfo);
//        System.out.println("数据库更改数据成功： "+Integer.parseInt(_id)+1);

    }
}
