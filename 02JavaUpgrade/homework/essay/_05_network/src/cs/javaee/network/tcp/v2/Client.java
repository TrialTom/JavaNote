package cs.javaee.network.tcp.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/20 20:30
 */

public class Client implements Runnable {
    Socket socket;
    byte[] bytes = new byte[1024];

    public Client(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            try {
                // 发送消息
                OutputStream out = socket.getOutputStream();
                out.write(s.getBytes());

                // 接收消息
                InputStream in = socket.getInputStream();
                int readData = in.read(bytes);
                String s1 = new String(bytes, 0, readData);
                System.out.println("接收来自" + socket.getInetAddress() + "的消息：" + s1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
