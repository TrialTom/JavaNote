package cs.javaee.network.udp.v1;

import java.io.IOException;
import java.net.*;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/18 20:48
 */

public class Sender {
    public static void main(String[] args) throws IOException {

        // 创建
        DatagramSocket datagramSocket = new DatagramSocket(8080);
        String s = "我想你，" ;

        int destPort = 8888;
        InetAddress ip = InetAddress.getByName("127.0.0.1");

        DatagramPacket packet = new DatagramPacket(s.getBytes(), 0, s.getBytes().length, ip, destPort);

        // 调用
        datagramSocket.send(packet);
        datagramSocket.close();
    }
}
