package com.cs.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：中文乱码，将响应体的编码格式传递过去
 * @date ：2024/05/10 9:38
 */
@WebServlet("/test01")
public class MyResponse01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type","text/html;charset=utf-8");
        resp.getWriter().println("你好，世界！");
    }
}
