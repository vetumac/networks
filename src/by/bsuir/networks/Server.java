package by.bsuir.networks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Server {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9090);
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        try {
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);
                String inputString = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + inputString);
                String outputString = "";
                Queue<String> characters = new LinkedList<>();
                characters.addAll(Arrays.asList(inputString.split("")));
                while (!characters.isEmpty()) {
                    String odd = characters.poll();
                    String even = characters.poll();
                    outputString = outputString +
                            (even == null ? "" : even) +
                            (odd == null ? "" : odd);
                }
                InetAddress ipAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = outputString.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, ipAddress, port);
                datagramSocket.send(sendPacket);
            }
        } finally {
            datagramSocket.close();
        }
    }
}
