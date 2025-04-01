package com.cs.login;

import sun.net.httpserver.HttpServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/16 17:16
 */
@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 进行分发
        String requestURI = req.getRequestURI();
        String op = requestURI.replace(req.getContextPath() + "/user/", "");

        if("register".equals(op)){
            register(req, resp);
        }
    }

    /**
     * 注册业务逻辑：获取提交过来的请求参数
     * @param req
     * @param resp
     */
    private void register(HttpServletRequest req, HttpServletResponse resp) {
        // 1. 直接获取 2. 使用beanUtils

    }
}
