package com.cs.shopping;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/14 17:15
 */

@WebServlet("/addCart")
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 乱码解决
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        // 获取传过来的id
        String id = req.getParameter("id");
        if (id == null || "".equals(id.trim())) {
            // 跳转回首页，提示id不能为空
            resp.getWriter().println("无效的id值,4秒钟后将跳转回首页");
            resp.setHeader("refresh", "4;url=" + req.getServletPath() + "/index");
            return;
        }
        List<String> listId = (List<String>) session.getAttribute("ListId");
        if (listId == null) {
            listId = new ArrayList<>();
            session.setAttribute("ListId", listId);
        }
        // 将id加入
        listId.add(id);
        resp.getWriter().println("加入购物车成功，即将返回首页！");
        resp.setHeader("refresh", "1;url=" + getServletContext().getContextPath() + "/index");

    }
}
