package com.cs.response;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：输入字节数据
 * @date ：2024/05/10 9:55
 */
@WebServlet("/test02")
public class MyResponse02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将照片写入
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("WEB-INF/1.png");
        ServletOutputStream outputStream = resp.getOutputStream();
        int length = 0;
        FileInputStream fileInputStream = new FileInputStream(realPath);
        byte[] bytes = new byte[1024];
        while ((length = fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes);
        }
    }
}
