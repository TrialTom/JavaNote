package request;

import org.apache.commons.beanutils.BeanUtils;
import request.bean.User;

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
 * @date ：2024/05/09 21:38
 */
@WebServlet("/plus")
public class MyServletRequestPlus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        System.out.println("user = " + user);
        resp.getWriter().println(user);
    }

}
