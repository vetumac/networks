package by.bsuir.networks.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(9090);
             Socket clientSocket = serverSocket.accept()) {


            try (InputStream in = clientSocket.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                 OutputStream os = clientSocket.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
                while (true) {
                    String inputString = reader.readLine();
                    System.out.println("Input string: " + inputString);
                    String[] coordinates = inputString.split(";");
                    Double x = Double.parseDouble(coordinates[0]);
                    Double y = Double.parseDouble(coordinates[1]);

                    String outputString = "Point on the board";
                    if (x > 0)
                        if (y > 0) outputString = "1";
                        else if (y < 0) outputString = "4";
                    if (x < 0)
                        if (y > 0) outputString = "2";
                        else if (y < 0) outputString = "3";

                    writer.write(outputString + "\n");
                    writer.flush();

                    System.out.println("Output string: " + outputString);
                }
            }
        }
    }
}
