package com.cs.loginmaven.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：处理请求
 * @date ：2024/05/15 16:34
 */
@WebServlet("/user/*")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取网络地址，去掉应用名，进行分流
        ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getContextPath();
        String requestURI = req.getRequestURI();
        // 替换掉应用名以及user
        String dest = requestURI.replace(contextPath + "/user/", "");
        if ("login".equals(dest)) {
            login(req, resp);
        } else if ("info".equals(dest)) {
            info(req, resp);
        } else if ("logout".equals(dest)) {
            loginout(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取网络地址，去掉应用名，进行分流
        ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getContextPath();
        String requestURI = req.getRequestURI();
        // 替换掉应用名以及user
        String dest = requestURI.replace(contextPath + "/user/", "");
        if ("login".equals(dest)) {
            login(req, resp);
        } else if ("register".equals(dest)) {
            try {
                RegisterServlet.dispose(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loginout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 注销
        HttpSession session = req.getSession();
        session.invalidate();
        resp.getWriter().println("即将返回登录");
        resp.setHeader("refresh", "1;url=" + req.getContextPath());
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        // 个人主页
        String username = (String) session.getAttribute("username");
        resp.getWriter().println("欢迎用户：" + username + ", 点击" + "<a href='" +
                req.getContextPath() + "/user/logout" + "'>注销</a>");
    }


    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取用户信息
        String username = req.getParameter("username");
        // 将用户名填入session
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        resp.getWriter().println("即将跳转至个人主页");
        resp.setHeader("refresh", "1;url=" + req.getContextPath() + "/user/info");
    }
}
