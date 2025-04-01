package com.cs.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author ：TrialCat
 * @description：使用Cookie获取上次访问的时间
 * @date ：2024/05/13 15:17
 */
@WebServlet("/time")
public class TimeDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            // 返回上次响应的时间
            for (Cookie cookie : cookies) {
                if("time".equals(cookie.getName())){
                    String name = cookie.getName();
                    String value = cookie.getValue();
                    resp.getWriter().println(name + ":" + new Date(Long.parseLong(value)));
                }
            }
        }
        // 导入此次响应的时间
        Date date = new Date();
        String[] splitDate = date.toString().split(" ");
        // cookie的value不能有空格
        Cookie time = new Cookie("time", System.currentTimeMillis() +"");
        resp.addCookie(time);
    }
}
