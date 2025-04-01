package request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/09 21:14
 */
@WebServlet("/test01")
public class MyServletRequest01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String[] parameterValues = req.getParameterValues(key);
            System.out.println(key + ":" + Arrays.toString(parameterValues));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String[] parameterValues = req.getParameterValues(key);
            System.out.println(key + ":" + Arrays.toString(parameterValues));
        }
    }
}
