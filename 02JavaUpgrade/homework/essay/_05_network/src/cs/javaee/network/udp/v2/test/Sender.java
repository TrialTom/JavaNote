package cs.javaee.network.udp.v2.test;

import cs.javaee.network.udp.v2.util.UtilityClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

/**
 * @author ：TrialCat
 * @description：发送方
 * @date ：2024/03/19 20:35
 */

public class Sender implements Runnable{
    String ip;
    int port;
    DatagramSocket datagramSocket;

    public Sender(String ip, int port, DatagramSocket datagramSocket) {
        this.ip = ip;
        this.port = port;
        this.datagramSocket = datagramSocket;
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true){
            String s = sc.nextLine();
            try {
                DatagramPacket packet = UtilityClass.encapsulatedOfPack(s, ip, port);
                datagramSocket.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
