package com.cs.logincase;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/15 9:12
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (auth(request)) {
            // 验证权限，是否需要登录
            HttpSession session = request.getSession();
            Object username = session.getAttribute("name");
            if (username == null) {
                // 拦截
                response.sendRedirect(request.getContextPath() + "/index.html");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean auth(HttpServletRequest request) {
        if ((request.getContextPath() + "/user/info").equals(request.getRequestURI())) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
