package com.cs.shopping;

import com.cs.shopping.bean.Product;

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
 * @date ：2024/05/14 17:26
 */
@WebServlet("/versionCart")
public class VersionCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        ServletContext servletContext = getServletContext();
        HttpSession session = req.getSession();
        ArrayList<String> listId = (ArrayList<String>) session.getAttribute("ListId");
        resp.getWriter().println("<a href='" + servletContext.getContextPath() + "/index" + "'>返回首页</a>");
        if (listId == null) {
            resp.getWriter().println("购物车为空!");
            return;
        }

        List<Product> products = (List<Product>) servletContext.getAttribute("products");

        for (Product product : products) {
            for (String id : listId) {
                if (id.equals(String.valueOf(product.getId()))) {
                    resp.getWriter().println(product);
                }
            }
        }
    }
}
