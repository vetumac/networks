package by.bsuir.networks.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private final static Map<String, String> dict;

    static {
        dict = new HashMap<>();
        dict.put("0", "Zero");
        dict.put("1", "One");
        dict.put("2", "Two");
        dict.put("3", "Three");
        dict.put("4", "Four");
        dict.put("5", "Five");
        dict.put("6", "Six");
        dict.put("7", "Seven");
        dict.put("8", "Eight");
        dict.put("9", "Nine");
    }

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

                    String outputString = dict.get(inputString);

                    writer.write(outputString + "\n");
                    writer.flush();

                    System.out.println("Output string: " + outputString);
                }
            }
        }
    }
}
