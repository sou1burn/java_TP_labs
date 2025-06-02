package TcpServer;

import java.io.*;
import java.net.*;
import java.util.*;
import ClientHandler.*;

public class TcpServer {
    private static final int PORT = 5050;
    private static volatile boolean running = true;
    private static Map<String, ClientHandler> clients = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started on port " + PORT);
            
            new Thread(() -> {
                try (Scanner scanner = new Scanner(System.in)) {
                    while (running) {
                        String command = scanner.nextLine();
                        if (command.equalsIgnoreCase("exit")) {
                            running = false;
                            shutdownServer(serverSocket);
                            // try {
                            //     new Socket("localhost", PORT).close();
                            // } catch (IOException ignored) {}
                        }
                    }
                }
            }).start();

            while (running) {
                try {
                    Socket socket = serverSocket.accept();
                    if (!running) break;
                    new Thread(() -> {
                        try {
                            ClientHandler handler = new ClientHandler(socket, clients);
                            handler.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    if (running) {
                        System.err.println("Error accepting connection: " + e.getMessage());
                    }   
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }

    private static void shutdownServer(ServerSocket socket) {
        synchronized (clients) {
            clients.values().forEach(handler -> handler.send("Server shutting down. Goodbye."));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            
            clients.values().forEach(ClientHandler::close);
            clients.clear();
        }
        try {
            if (socket != null && !socket.isClosed()) 
                socket.close();
            
        } catch (IOException e) {
            System.err.println("Error closing server socket: " + e.getMessage());
        }
    }
}
