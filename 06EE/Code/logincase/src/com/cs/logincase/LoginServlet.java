package com.cs.logincase;

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
 * @description：TODO
 * @date ：2024/05/14 20:46
 */

@WebServlet("/user/*")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req.setCharacterEncoding("utf-8");
        // resp.setContentType("text/html;charset=utf-8");
        String requestURI = req.getRequestURI();
        // 获取context
        ServletContext servletContext = getServletContext();
        String dest = requestURI.replace(servletContext.getContextPath() + "/user/", "");
        // 请求分发
        if("login".equals(dest)){
            login(req, resp);
        }else if("logout".equals(dest)){
            logout(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req.setCharacterEncoding("utf-8");
        // resp.setContentType("text/html;charset=utf-8");
        String requestURI = req.getRequestURI();
        // 获取context
        ServletContext servletContext = getServletContext();
        String dest = requestURI.replace(servletContext.getContextPath() + "/user/", "");
        // 请求分发
        if("login".equals(dest)){
            login(req, resp);
        }else if("logout".equals(dest)){
            logout(req, resp);
        }else if ("info".equals(dest)){
            info(req, resp);
        }
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        resp.getWriter().println("欢迎用户:" + session.getAttribute("name")
                + "点击此处" + "<a href='" + getServletContext().getContextPath() + "/user/logout" + "'>注销</a>");
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // req.setCharacterEncoding("utf-8");
        // resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        session.invalidate();
        resp.getWriter().println("即将跳转回首页登录");
        resp.setHeader("refresh", "2;url=" + getServletContext().getContextPath() + "/index.html");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // req.setCharacterEncoding("utf-8");
        // resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");

        HttpSession session = req.getSession();
        session.setAttribute("name", username);
        System.out.println(getServletContext().getContextPath());
        resp.setHeader("refresh", "0;url=" + getServletContext().getContextPath() + "/user/info");
    }
}
