package com.cs.shopping;

import com.cs.shopping.bean.Product;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/14 7:58
 */
@WebServlet(value = "/index", loadOnStartup = 1)
public class Demo01 extends HttpServlet {
    @Override
    public void init() throws ServletException {
        List<Product> products = new LinkedList<>();
        Product product = new Product(0, "iphone", "5119.0", "A new phone");
        Product p1 = new Product(1, "mate40", "5119.0", "A good phone");
        Product p2 = new Product(2, "p40", "9999.0", "A good phone");
        products.add(product);
        products.add(p1);
        products.add(p2);
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("products", products);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        List<Product> products = (List<Product>) servletContext.getAttribute("products");
        for (Product product : products) {
            resp.getWriter().println("<a href='" + servletContext.getContextPath() + "/item?id=" +
                    product.getId() + "'>" + product.getName() + "</a>");
        }
    }
}
