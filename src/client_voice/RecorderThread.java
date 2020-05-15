package client_voice;

import javax.sound.sampled.TargetDataLine;
import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RecorderThread extends Thread {
    public TargetDataLine audioIn = null;
    public DatagramSocket socketOut;
    byte byteBuff[] = new byte[512];

    public InetAddress serverIp;
    public int serverPort;

    @Override
    public void run() {
        int i = 0;
        System.out.println("run called");
        while (ClientWindow.calling) {
            audioIn.read(byteBuff, 0, byteBuff.length);
            DatagramPacket data = new DatagramPacket(byteBuff, byteBuff.length, serverIp, serverPort);
            System.out.println("Send# " + i++);
            try {
                socketOut.send(data);
            } catch (Exception e) {
                System.out.println("ERROR IN RecorderThread run()");
                e.printStackTrace();
            }

        }
    }
}
