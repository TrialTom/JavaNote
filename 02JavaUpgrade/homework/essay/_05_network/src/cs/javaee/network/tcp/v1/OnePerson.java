package cs.javaee.network.tcp.v1;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/20 11:37
 */

public class OnePerson {
    public static void main(String[] args) throws IOException {
        // 接收
        ServerSocket serverSocket = new ServerSocket(8888);
        new Thread(new Servet(serverSocket)).start();
        // 发送
        // Socket socket = new Socket("127.0.0.1", 8080);
        // new Thread(new Client(socket)).start();

    }
}
