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
                    String[] expression = inputString.split(";");
                    String operation = expression[0];
                    Double num1 = Double.parseDouble(expression[1]);
                    Double num2 = Double.parseDouble(expression[2]);

                    String outputString = String.valueOf(calculate(operation, num1, num2));

                    writer.write(outputString + "\n");
                    writer.flush();

                    System.out.println("Output string: " + outputString);
                }
            }
        }
    }

    private static Double calculate(String operation, Double num1, Double num2) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        return null;
    }
}
