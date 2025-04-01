package cs.javaee.network.tcp.v1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/03/20 11:23
 */

public class Client implements Runnable {
    Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String s = sc.nextLine();
                OutputStream out = socket.getOutputStream();
                out.write(s.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
