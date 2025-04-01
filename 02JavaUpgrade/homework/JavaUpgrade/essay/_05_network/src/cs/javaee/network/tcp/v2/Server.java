package cs.javaee.network.tcp.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/20 20:30
 */

public class Server implements Runnable {
    ServerSocket serverSocket;
    byte[] bytes = new byte[1024];

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            Scanner sc = new Scanner(System.in);
            while (true){
                // 接收消息
                InputStream in = socket.getInputStream();
                int readData = in.read(bytes);
                String s = new String(bytes, 0, readData);
                System.out.println("接收来自" + socket.getInetAddress() + "的消息：" + s);
                // 响应消息
                OutputStream out = socket.getOutputStream();
                out.write(sc.nextLine().getBytes());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
