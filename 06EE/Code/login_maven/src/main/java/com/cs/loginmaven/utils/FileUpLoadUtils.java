package com.cs.loginmaven.utils;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author ：TrialCat
 * @description：处理文件上传的工具类
 * @date ：2024/05/15 19:58
 */

public class FileUpLoadUtils {

    /**
     * 获取前端传递的请求，将数据进行封装整理
     * @param request ：获取前端传递过来的请求
     * @return ： 将form表单提交的数据装入map中
     * @throws Exception
     */
    public static Map<String, String> parseRequest(HttpServletRequest request) throws Exception {
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            return null;
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = request.getServletContext();
        // Or "javax.servlet.context.tempdir" for javax
        File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        HashMap<String, String> map = new HashMap<>();
        // Parse the request
        List<FileItem> items = upload.parseRequest(request);

        // Process the uploaded items
        for (FileItem item : items) {
            if (item.isFormField()) {
                processFormField(item, map, request);
            } else {
                // 提交的是文件
                processUploadedFile(item, map, request);
            }
        }
        return map;
    }

    private static void processUploadedFile(FileItem item, HashMap<String, String> map, HttpServletRequest request) throws Exception {
        // 将文件保存到image目录下
        String fieldName = item.getName();

        // 随机命名，解决重名问题
        String uid = UUID.randomUUID().toString();
        fieldName = uid + "-" + fieldName;

        // 利用hash值将文件分散，提高查找效率
        int hashCode = fieldName.hashCode();
        String hash = Integer.toHexString(hashCode);
        char[] chars = hash.toCharArray();
        String basePath = "image";
        for (char aChar : chars) {
            basePath = basePath + "/" + aChar;
        }

        String relativePath = basePath + "/" + fieldName;
        // 获取绝对路径
        String realPath = request.getServletContext().getRealPath(relativePath);
        File file = new File(realPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        item.write(file);
        // 将relativePath保存到map中，进而方便保存到数据库
        map.put("fileName", relativePath);
    }

    private static void processFormField(FileItem item, HashMap<String, String> map, HttpServletRequest request) throws Exception {
        String fieldName = item.getFieldName();
        String value = null;

        // 处理表单提交数据
        // 解决表单数据乱码问题
        value = item.getString("utf-8");
        map.put(fieldName, value);
    }
}
