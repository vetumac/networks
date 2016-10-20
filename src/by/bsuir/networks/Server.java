package by.bsuir.networks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

                char[] characters = inputString.trim().toCharArray();
                if (characters.length > 15) {
                    for (char character : characters)
                        if (character < 97 || character > 122) outputString = outputString + character;
                } else outputString = inputString;

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
