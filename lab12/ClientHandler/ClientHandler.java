package ClientHandler;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler extends Thread{
    private Socket socket;
    private String username;
    private BufferedReader in;
    private BufferedWriter out;
    private Map<String, ClientHandler> clients;

    public ClientHandler(Socket socket, Map<String, ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.clients = clients;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            out.write("Enter your username: \n");
            out.flush();
        } catch(IOException e) {
            e.printStackTrace(); 
        }
        
        try {
            username = in.readLine();
            if (username == null || username.trim().isEmpty()) {
                out.write("Username cannot be empty. Bye Bye.\n");
                out.flush();
                socket.close();
                System.exit(1);
                return;
            }

            synchronized (clients) {
                if (clients.containsKey(username)) {
                    out.write("Username already taken. Connection closed.\n");
                    out.flush();
                    socket.close();
                    return;
                }
                clients.put(username, this);
            }

            broadcast("User " + username + " joined the chat.", null);

            String message;
            while ((message = in.readLine()) != null) {
                if (message.startsWith("@senduser ")) {
                    String[] parts = message.split(" ", 3);
                    if (parts.length < 3) {
                        out.write("Usage: @senduser <username> <message>\n");
                        out.flush();
                        continue;
                    }

                    String targetUser = parts[1];
                    String privateMessage = parts[2];

                    ClientHandler targetHandler = clients.get(targetUser);
                    if (targetHandler != null) {
                        targetHandler.send("[Private from " + username + "]: " + privateMessage);
                    } else {
                        out.write("User not found.\n");
                        out.flush();
                    }
                } else
                    broadcast("[" + username + "]: " + message, this);
            }
        } catch (IOException e) {
            System.out.println("Connection with " + username + " lost.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (username != null) {
                clients.remove(username);
                broadcast("User " + username + " left the chat.", null);
            }
        }
    }

    private void broadcast(String message, ClientHandler exclude) {
        synchronized (clients) {
            for (ClientHandler client : clients.values()) {
                if (client != exclude) 
                    client.send(message);
            }
        }
    }

    public void send(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
    }
}

}
