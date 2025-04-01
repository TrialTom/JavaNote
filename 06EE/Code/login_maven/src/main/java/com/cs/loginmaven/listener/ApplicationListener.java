package com.cs.loginmaven.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/15 16:37
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取配置文件的绝对路径
        ServletContext servletContext = sce.getServletContext();
        // 获取类加载器
        ClassLoader classLoader = ApplicationListener.class.getClassLoader();
        // 获取classpath目录下指定文件的资源信息
        String realPath = classLoader.getResource("auth.txt").getPath();
        List<String> urls = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(realPath)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                // 将url导入
                urls.add(line);
            }
            servletContext.setAttribute("urls", urls);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
