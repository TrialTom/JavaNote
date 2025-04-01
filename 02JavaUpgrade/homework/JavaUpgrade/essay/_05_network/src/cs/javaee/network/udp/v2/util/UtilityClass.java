package cs.javaee.network.udp.v2.util;

import com.sun.corba.se.impl.encoding.CodeSetConversion;

import javax.security.auth.callback.PasswordCallback;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * @author ：TrialCat
 * @description：发送和接收的工具类
 * @date ：2024/03/19 20:20
 */

public class UtilityClass {

    /**
     * create by: TrialCat
     * description: 封装成包
     * create time: 2024/3/19 20:24
     */
    public static DatagramPacket encapsulatedOfPack(String s, String address, int port)throws IOException {
        InetAddress ip = InetAddress.getByName(address);

        DatagramPacket packet = new DatagramPacket(s.getBytes(), 0, s.getBytes().length, ip, port);
        return packet;
    }

    /**
     * create by: TrialCat
     * description: 重写
     * create time: 2024/3/19 20:48
     */
    public static DatagramPacket encapsulatedOfPack(){
        byte[] bytes = new byte[1024];
        return new DatagramPacket(bytes, bytes.length);
    }

    /**
     * create by: TrialCat
     * description: 包的解析
     * create time: 2024/3/19 20:29
     */
    public static String analysisOfPack(DatagramPacket packet){
        byte[] data = packet.getData();
        int offset = packet.getOffset();
        int length = packet.getLength();
        String s = new String(data, offset, length);
        return s;
    }
}
