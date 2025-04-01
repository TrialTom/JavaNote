package com.cs.loginmaven.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/15 16:56
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 在context域中查找需要认证的uri
        ServletContext servletContext = servletRequest.getServletContext();
        ArrayList<String> urls = (ArrayList<String>) servletContext.getAttribute("urls");
        // 判断是否进行了登录
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String dest = request.getRequestURI().replace(request.getContextPath(), "");
        for (String url : urls) {
            if (url.equals(dest)) {
                // session为空,没有登录信息
                HttpSession session = request.getSession();
                Object username = session.getAttribute("username");
                if (username == null) {
                    response.setHeader("refresh","0;url=" + request.getContextPath());
                    return;
                }
            }
        }
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
