package com.cs.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author ：TrialCat
 * @description：定时刷新
 * @date ：2024/05/10 10:05
 */
@WebServlet("/test04")
public class Refresh extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("refresh", "1");
        resp.getWriter().println(new Date());
        resp.setHeader("refresh", "5;url="+ req.getContextPath() + "/1.html");
    }
}
