package cs.javaee.network.udp.v2.test;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/20 10:47
 */

public class OnePerson {
    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(8080);
        String ip = "127.0.0.1";
        int port = 8888;
        new Thread(new Sender(ip, port, datagramSocket)).start();
        new Thread(new Receiver(datagramSocket)).start();
    }
}
