package com.cs.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author ：TrialCat
 * @description：文件上传
 * @date ：2024/05/11 9:41
 */
@WebServlet("/submit")
public class MyUpload01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 检查是否有文件上传请求
        boolean content = ServletFileUpload.isMultipartContent(req);

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        // 每个应用被加载到tomcat服务器之后，tomcat会给每个应用设置一个临时目录，并且把该目录的位置放入每个应用对应的context域中
        File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");

        diskFileItemFactory.setRepository(repository);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    processFormField(item);
                } else {
                    processUpLoadFile(item, req);
                    resp.getWriter().println("<div>上传成功</div>");
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void processFormField(FileItem item) throws UnsupportedEncodingException {
        item.getString("UTF-8");
        String name = item.getFieldName();
        String string = item.getString();

        System.out.println(name + ":" + string);
    }

    private void processUpLoadFile(FileItem item, HttpServletRequest req) throws Exception {
        item.getString("UTF-8");
        String name = item.getName();
        String fieldName = item.getFieldName();
        String contentType = item.getContentType();
        long size = item.getSize();
        boolean inMemory = item.isInMemory();

        System.out.println("name" + ":" + name);
        System.out.println("fieldName" + ":" + fieldName);
        System.out.println("contentType" + ":" + contentType);
        System.out.println("size" + ":" + size);
        System.out.println("inMemory" + ":" + inMemory);

        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("img/1.png");
        File file = new File(realPath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int length = 0;
        byte[] bytes = new byte[1024];
        InputStream inputStream = item.getInputStream();
        if (file.getParentFile().exists()) {
        } else {
            // 创建文件
            file.getParentFile().mkdirs();
        }
        while ((length = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
        }
        fileOutputStream.close();
        inputStream.close();
    }
}
