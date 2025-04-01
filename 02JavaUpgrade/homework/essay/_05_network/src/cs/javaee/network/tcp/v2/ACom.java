package cs.javaee.network.tcp.v2;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：TrialCat
 * @description：客户端与服务端相互传输数据
 * @date ：2024/03/20 20:45
 */

public class ACom {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new Client(socket));
    }
}
