package com.cs.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/10 10:19
 */
@WebServlet("/test03")
public class MyResponse03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("登录成功，即将在三秒后跳转到个人主页，若跳转失败，请手动跳转.");

        resp.setHeader("refresh", "3;url=" + req.getContextPath() + "/person.html");
    }
}
