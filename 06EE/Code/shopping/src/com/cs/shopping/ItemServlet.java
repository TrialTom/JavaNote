package com.cs.shopping;

import com.cs.shopping.bean.Product;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/14 11:05
 */
@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        if(id == null || "".equals(id.trim())){
            // 跳转回首页，提示id不能为空
            resp.getWriter().println("无效的id值,4秒钟后将跳转回首页");
            resp.setHeader("refresh", "1;url=" + req.getServletPath() + "/index");
            return;
        }
        ServletContext servletContext = getServletContext();
        System.out.println(id);
        List<Product> products = (List<Product>) servletContext.getAttribute("products");
        // 返回商品详情页面
        for (Product product : products) {
            if(id.equals(String.valueOf(product.getId()))){
                resp.getWriter().println(product);
            }
        }
        resp.getWriter().println("<a href='"+ servletContext.getContextPath() + "/index" +"'>返回首页</a>");
        resp.getWriter().println("<a href='"+ servletContext.getContextPath() + "/addCart?id=" + id  + "'>加入购物车</a>");
        resp.getWriter().println("<a href='"+ servletContext.getContextPath() + "/versionCart" + "'>查看购物车</a>");
    }
}
