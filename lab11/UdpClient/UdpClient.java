package UdpClient;

import java.net.*;
import java.util.Scanner;

public class UdpClient {
    private static final int BUFSIZE = 1024;

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java UdpClient.UdpClient <server_address> <server_port>");
            return;
        }

        InetAddress serverAddress = InetAddress.getByName(args[0]);
        int serverPort = Integer.parseInt(args[1]);

        DatagramSocket socket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);

        final String[] name = {"Client"};

        Thread receiver = new Thread(() -> {
            byte[] buffer = new byte[BUFSIZE];
            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    if (msg.equals("@quit")) {
                        System.out.println("Server ended chat");
                        System.exit(0);
                    } else 
                        System.out.println(msg);
                        
                } catch (Exception e) {
                    if (!socket.isClosed()) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        });

        receiver.start();

        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("@name ")) {
                name[0] = input.substring(6);
                socket.send(new DatagramPacket(input.getBytes(), input.length(), serverAddress, serverPort));
            } else if (input.equals("@quit")) {
                byte[] quitMessage = "@quit".getBytes();
                socket.send(new DatagramPacket(quitMessage, quitMessage.length, serverAddress, serverPort));
                System.out.println("You left the chat.");
                socket.close();
                scanner.close();
                System.exit(0);
            } else {
                byte[] message = input.getBytes();
                socket.send(new DatagramPacket(message, message.length, serverAddress, serverPort));
            }
        }
    }
}
