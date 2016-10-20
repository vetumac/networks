package by.bsuir.networks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner stringScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter string: ");
            String outputString = stringScanner.nextLine();

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress ipAddress = InetAddress.getByName("localhost");
            byte[] sendData = outputString.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9090);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            System.out.println(new String(receivePacket.getData()));
        }
    }
}
