package by.bsuir.networks.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Enter string: ");
            Scanner stringScanner = new Scanner(System.in);
            String outputString = stringScanner.nextLine();
            String inputString;

            try (Socket clientSocket = new Socket("localhost", 9090)) {

                try (OutputStream os = clientSocket.getOutputStream();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
                    writer.write(outputString);
                    writer.flush();
                }

                try (InputStream in = clientSocket.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    inputString = reader.readLine();
                }
            }
            System.out.println(inputString);
        }
    }
}
