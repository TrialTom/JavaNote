package cs.javaee.network.udp.v1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/18 20:49
 */

public class Receiver {
    public static void main(String[] args) throws IOException {

        // 创建
        DatagramSocket datagramSocket = new DatagramSocket(8888);

        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, 1024);


        // 调用
        System.out.println("----");
        datagramSocket.receive(packet) ;
        System.out.println("----");

        byte[] data = packet.getData();
        int length = packet.getLength();
        int offset = packet.getOffset();
        String s = new String(data, offset, length);
        System.out.println("s = " + s);

        datagramSocket.close();
    }
}
