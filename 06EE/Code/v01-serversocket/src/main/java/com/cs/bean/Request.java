package com.cs.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/07 15:42
 */

public class Request {
    Socket socket;
    String inputString;
    /**
     * 请求行的请求方法
     */
    String method;
    /**
     * 请求资源
      */
    String requestUrl;
    /**
     * HTTP版本
     */
    String httpVersion;

    public Request(ServerSocket client) throws IOException {
        socket = client.accept();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);
        inputString = new String(bytes, 0, read);
    }

    public void analysisRequestOfLine(){
        int index = inputString.indexOf("\r\n");
        String requestLine = inputString.substring(0, index);
        String[] composeOfLine = requestLine.split(" ");
        method = composeOfLine[0];
        requestUrl = composeOfLine[1];
        if(requestUrl.indexOf("?") != -1){
            requestUrl = requestLine.substring(0, requestLine.indexOf("?"));
        }
        httpVersion = composeOfLine[2];
    }

    public OutputStream getOutPut() throws IOException {
        return socket.getOutputStream();
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }
}
