package cs.javaee.network.udp.v2.test;


import cs.javaee.network.udp.v2.util.UtilityClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author ：TrialCat
 * @description：接收方
 * @date ：2024/03/19 20:35
 */

public class Receiver implements Runnable {
    DatagramSocket datagramSocket;

    public Receiver(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }


    @Override
    public void run() {
        while (true) {
            // 创建接收包
            DatagramPacket packet = UtilityClass.encapsulatedOfPack();
            try {
                datagramSocket.receive(packet);
                String s = UtilityClass.analysisOfPack(packet);
                System.out.println("接收来自：" + packet.getAddress() + "的消息： " + s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
