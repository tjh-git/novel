package filter;

import org.common.Utilty;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"*.jsp","*.html"},dispatcherTypes = {})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("LoginFilter doFilter");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = request.getRequestURI();

        System.out.println("请求的url：" + url);
        /*登录页面不需要过滤*/

        int idx = url.lastIndexOf("/");
        String endWith = url.substring(idx + 1);

        if (!endWith.equals("index.html")) {
            /*不是登录页面  进行拦截处理*/

            System.out.println("不是登录页面，进行拦截处理");

            if (!isLogin(request)) {
                System.out.println("没有登录过或者账号密码错误，跳转到登录界面");
                response.sendRedirect(((HttpServletRequest) req).getContextPath()+"/index.html");
            } else {
                System.out.println("已经登录，进行下一步");
                chain.doFilter(req, resp);
            }

        } else {
            System.out.println("是登录页面，不进行拦截处理");
            chain.doFilter(req, resp);
        }




    }

    /**
     * 判断是否登录
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        String account = Utilty.readCookie(request,"reader");
        System.out.println("zhangaho : "+account);

        if (account.equals("") || account == null) {
            return false;
        } else{
            return true;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
