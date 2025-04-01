package com.cs.reflectionwork;

import com.cs.reflectionwork.bean.User;
import com.cs.reflectionwork.util.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/10 11:24
 */

@WebServlet("/reflect")
public class MyReflection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        User user = new User();
        try {
            BeanUtils.toBean(user, req.getParameterMap());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(user);
    }
}
