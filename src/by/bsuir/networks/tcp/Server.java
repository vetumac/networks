package by.bsuir.networks.tcp;

import java.io.*;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        try (Socket clientSocket = new Socket("localhost", 9090)) {

            while (true) {
                String inputString;

                try (InputStream in = clientSocket.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    inputString = reader.readLine();
                }
                System.out.println("Input string: " + inputString);
                String[] coordinates = inputString.split(";");
                Double x = Double.parseDouble(coordinates[0]);
                Double y = Double.parseDouble(coordinates[1]);

                String outputString = null;
                try (OutputStream os = clientSocket.getOutputStream();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
                    writer.write(outputString);
                    writer.flush();
                }

                System.out.println(inputString);
            }
        }
    }
}
