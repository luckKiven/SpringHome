package cn.tedu.kiven.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 判断是否登录用的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request .getSession();
        if(session.getAttribute("uid")==null) {
            response.sendRedirect("/sg/Login.html");
            return false;
        }
        return true;
    }
}
