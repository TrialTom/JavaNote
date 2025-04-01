package com.cs.write;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author ：TrialCat
 * @description：将HTTP请求写入本地文件
 * @date ：2024/05/10 15:52
 */
@WebServlet("/submit")
public class WriteFile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(req.getMethod());
        stringBuffer.append(req.getRequestURI());
        stringBuffer.append(req.getProtocol());
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            String parameterValues = req.getHeader(key);
            stringBuffer.append(key + ":" + parameterValues + "/n");
        }
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("1.txt");
        File file = new File(realPath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(stringBuffer.toString().getBytes());

        System.out.println("---------");
        fileOutputStream.close();

    }
}
