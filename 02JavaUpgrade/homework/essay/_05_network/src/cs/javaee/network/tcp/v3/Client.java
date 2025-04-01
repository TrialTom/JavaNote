package cs.javaee.network.tcp.v3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author ：TrialCat
 * @description：客户端，上传文件到服务端
 * @date ：2024/03/20 20:59
 */

public class Client {
    public static void main(String[] args) throws IOException {
        // 创建文件输入流对象
        File file = new File("C:\\Users\\Lenovo\\Pictures\\Screenshots\\test01.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        // 创建客户端对象
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream out = socket.getOutputStream();
        // 将文件读入内存，并将文件上传
        int readData;
        byte[] bytes = new byte[1024];
        while ((readData = fileInputStream.read(bytes)) != -1){
            out.write(bytes);
        }
    }
}
