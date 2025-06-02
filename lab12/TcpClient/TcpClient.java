package TcpClient;

import java.io.*;
import java.net.*;

public class TcpClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5050;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            
            String welcome = in.readLine();
            System.out.print(welcome);

            String usrName = input.readLine();
            out.write(usrName + "\n");
            out.flush();
            
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                } finally {
                    try {
                        System.in.close(); 
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            String userInput;
            while ((userInput = input.readLine()) != null) {
                out.write(userInput + "\n");
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("Could not connect to server.");
        }
    }
}
