package com.cs.fileupload;


import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/10 18:37
 */
@WebServlet("/submit1")
public class MyUpLoad extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("image/1.png");
        File file = new File(realPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        // 写入数据
        ServletInputStream inputStream = req.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes, 0, length);
        }
        fileOutputStream.close();
        inputStream.close();
    }
}
