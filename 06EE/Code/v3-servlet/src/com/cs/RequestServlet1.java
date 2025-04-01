package com.cs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/09 10:58
 */

public class RequestServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取服务器主机信息
        String localAddr = req.getLocalAddr();
        // 端口号
        int localPort = req.getLocalPort();
        // 获取客户端主机信息
        String remoteAddr = req.getRemoteAddr();
        // 获取客户机的端口号
        int remotePort = req.getRemotePort();
    }
}
