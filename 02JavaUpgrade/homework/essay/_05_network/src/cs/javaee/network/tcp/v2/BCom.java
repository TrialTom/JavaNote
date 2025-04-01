package cs.javaee.network.tcp.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/20 20:45
 */

public class BCom {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new Server(serverSocket));
    }
}
