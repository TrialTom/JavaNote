package com.cs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/09 9:23
 */

@WebServlet("/servlet1")
public class MyServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(new Date());

        // 获取请求行
        String method = req.getMethod();
        String requestURI = req.getRequestURI();
        StringBuffer requestURL = req.getRequestURL();
        String protocol = req.getProtocol();
        // 获取请求头
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String element = headerNames.nextElement();
            String header = req.getHeader(element);
            System.out.println(element + ":" + header);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
