package com.cs.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：响应行，响应头，响应体
 * @date ：2024/05/10 9:26
 */
@WebServlet("/temp")
public class MyResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(404);
        resp.setHeader("Server","AlIBaBa");
        resp.getWriter().println("<div style='color:red'>Not Found</div>");
    }
}
