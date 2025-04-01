package com.cs;

import com.cs.bean.Request;

import java.io.*;
import java.net.ServerSocket;

/**
 * @author ：TrialCat
 * @description： 简略的服务端
 * @date ：2024/05/07 15:41
 */

public class MyServerSocket {
    public static void main(String[] args) throws IOException {
        // 建立连接
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {

            // 获取socket
            Request client = new Request(serverSocket);

            client.analysisRequestOfLine();

            System.out.println("client.getMethod() = " + client.getMethod());
            System.out.println("client.getRequestUrl() = " + client.getRequestUrl());
            System.out.println("client.getHttpVersion() = " + client.getHttpVersion());

            // 响应请求
            OutputStream outPut = client.getOutPut();
            StringBuffer buffer = new StringBuffer();
            String requestUrl = client.getRequestUrl();
            File file = new File(requestUrl.substring(1));
            // 判断文件是否存在，且不是目录文件
            if(file.exists() && file.isFile()){
                buffer.append("HTTP1.0 200 OK\r\n");
                buffer.append("Content-Type:text/html\r\n");
                buffer.append("\r\n");
                outPut.write(buffer.toString().getBytes("UTF-8"));

                FileInputStream fileInputStream = new FileInputStream(file);
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = fileInputStream.read(bytes)) != -1) {
                    outPut.write(bytes, 0, len);
                }
                continue;
            }
            // 文件不存在
            buffer.append("HTTP/1.1 404 Not Found\r\n");
            buffer.append("Content-Type:text/html\r\n");
            buffer.append("\r\n");
            buffer.append("<div style='color:red'>Not Found</div>");
            outPut.write(buffer.toString().getBytes("UTF-8"));
        }


    }
}
