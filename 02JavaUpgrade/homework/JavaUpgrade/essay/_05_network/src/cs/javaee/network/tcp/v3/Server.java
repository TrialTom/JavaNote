package cs.javaee.network.tcp.v3;

import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：TrialCat
 * @description：服务端，接收文件并保存到磁盘
 * @date ：2024/03/20 21:00
 */

public class Server {
    @Test
    public void Test01() throws IOException {
        // 创建服务端对象
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket accept = serverSocket.accept();
        InputStream in = accept.getInputStream();
        // 创建问文件输入流对象
        FileOutputStream out = new FileOutputStream("copy.jpg");
        // 存储文件
        int readData;
        byte[] bytes = new byte[1024];
        while ((readData = in.read(bytes)) != -1){
            out.write(bytes);
        }
    }
}
