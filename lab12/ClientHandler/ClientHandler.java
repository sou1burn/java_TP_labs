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
    private static final char[][] grid = new char[3][3];

    static {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                grid[i][j] = ' ';
        }
    }

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
                } else if (message.startsWith("@draw")) {
                    handleDraw(message);
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
            close();
        }
    }

    private void handleDraw(String message) throws IOException {
        String[] parts = message.split(" ");
        if (parts.length != 3) {
            out.write("Usage: @draw <col> <row> (1-indexed)\n");
            out.flush();
            return;
        }
        int col, row;
        try {
            col = Integer.parseInt(parts[1]) - 1;
            row = Integer.parseInt(parts[2]) - 1;
        } catch (NumberFormatException ex) {
            out.write("Coordinates must be numbers.\n");
            out.flush();
            return;
        }
        if (col < 0 || col >= 3 || row < 0 || row >= 3) {
            out.write("Coordinates out of range. Use from 1 to 3.\n");
            out.flush();
            return;
        }
        synchronized (grid) {
            if (grid[row][col] != ' ') {
                out.write("Cell already occupied.\n");
                out.flush();
                return;
            }
            grid[row][col] = 'X';
        }
        broadcast("User " + username + " drew at (" + (col + 1) + "," + (row + 1) + ").", null);
        broadcastGrid();
    }

    private void broadcastGrid() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Grid:\n");
        synchronized (grid) {
            for (int i = 0; i < 3; i++) {
                sb.append("|");
                for (int j = 0; j < 3; j++) {
                    sb.append(grid[i][j] == ' ' ? '_' : grid[i][j]);
                    sb.append("|");
                }
                sb.append("\n");
            }
        }
        broadcast(sb.toString(), null);
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
