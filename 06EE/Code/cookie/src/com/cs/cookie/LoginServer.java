package com.cs.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：登录
 * @date ：2024/05/12 16:23
 */
@WebServlet("/user/*")
public class LoginServer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求分发
        String requestURI = req.getRequestURI();
        String serverName = requestURI.replace(req.getContextPath() + "/user/", "");
        System.out.println(serverName);
        if("login".equals(serverName)){
            login(req, resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        // 获取表单数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 添加至Cookie
        Cookie cookie = new Cookie("username", username);
        resp.addCookie(cookie);

        resp.getWriter().println("登录成功，5秒钟之后跳转至" + "<a href='"+req.getContextPath() + "/user" +"'>个人主页</a>");
        resp.setHeader("refresh", "2;url=" + req.getContextPath() +"/user/info");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            // 获取cookie中的参数
            for (Cookie cookie : cookies) {
                if("username".equals(cookie.getName())){
                    resp.getWriter().println("欢迎光临" + cookie.getValue());
                }
            }
        }
    }
}
