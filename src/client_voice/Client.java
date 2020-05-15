package client_voice;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public int port = 8000;
    public String serverAddress = "127.0.0.1";

    public static AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        int sampleSizeInBits = 16;
        int channel = 2;
        boolean isSigned = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channel, isSigned, bigEndian);

    }

    TargetDataLine audioIn;

    public void initAudio() {
        try {
            System.out.println("initAudio() called");
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("not supported");
                System.exit(0);
            }

            audioIn = (TargetDataLine) AudioSystem.getLine(info);
            audioIn.open(format);
            audioIn.start();
            RecorderThread r = new RecorderThread();
            InetAddress serverInet = InetAddress.getByName(serverAddress);
            r.audioIn = audioIn;
            r.socketOut = new DatagramSocket();
            r.serverIp = serverInet;
            r.serverPort = port;
            ClientWindow.calling = true;
            r.start();
        }catch (Exception e) {
            System.out.println("ERROR IN initAudio()");
            e.printStackTrace();
        }


    }
}
