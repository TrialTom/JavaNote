package cs.javaee.network.tcp.v1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/20 11:30
 */

public class Servet implements Runnable {
    ServerSocket serverSocket;

    public Servet(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    @Override
    public void run() {
        byte[] bytes = new byte[1023];
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            InputStream in = null;
            try {
                in = socket.getInputStream();
                int readData = in.read(bytes);
                System.out.println("接收来自" + socket.getInetAddress() +
                        "的消息：" + new String(bytes, 0, readData));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
