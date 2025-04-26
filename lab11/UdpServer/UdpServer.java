package UdpServer;

import java.net.*;
import java.util.Scanner;

public class UdpServer {
    private static final int BUFSIZE = 1024;

    public static void main(String[] args) throws Exception
    {
        if (args.length != 1) {
            System.out.println("Usage: java UdpServerUdpServer <port>");
            return;
        }

        DatagramSocket sock = new DatagramSocket(Integer.parseInt(args[0]));
        Scanner scanner = new Scanner(System.in);

        System.out.println("UdpServer started on port:" + args[0]);

        final String[] name = {"Server"};
        final String[] clientName = {"Client"};
        final InetAddress[] clientAddress = new InetAddress[1];
        final int[] clientPort = new int[1];

        Thread receiver = new Thread(() -> {
            byte[] buffer = new byte[BUFSIZE];
            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, BUFSIZE);
                    sock.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    clientAddress[0] = packet.getAddress();
                    clientPort[0] = packet.getPort();

                    if (msg.startsWith("@name ")) {
                        clientName[0] = msg.substring(6);
                        System.out.println("Client new name is: " + clientName[0]);
                    } else if (msg.equals("@quit")) {
                        System.out.println("Client named " + clientName[0] +" left");
                        clientAddress[0] = null;
                        clientPort[0] = 0;
                        clientName[0] = "";
                    } else 
                        System.out.println(clientName[0] + ": " + msg);

                } catch (Exception e) {
                    if (!sock.isClosed()) 
                        System.out.println("Error: " + e.getMessage());
                }
            }
        });

        receiver.start();

        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("@name "))
                name[0] = input.substring(6);
            else if (input.equals("@quit")) {
                byte[] quitMessage = "@quit".getBytes();
                if (clientAddress[0] != null) 
                    sock.send(new DatagramPacket(quitMessage, quitMessage.length, clientAddress[0], clientPort[0]));
                System.out.println("Stopping server");
                sock.close();
                scanner.close();
                System.exit(0);
            } else if (input.equals("@kill")) {
                if (clientAddress[0] != null) {
                    byte[] killMessage = "@kill".getBytes();
                    DatagramPacket killPacket = new DatagramPacket(killMessage, killMessage.length, clientAddress[0], clientPort[0]);
                    sock.send(killPacket);
                    System.out.println("Sent @kill to client.");
                } else 
                    System.out.println("No client connected to kill.");
            } else if (clientAddress[0] != null) {
                String msg = name[0] + ": "+ input;
                byte[] data = msg.getBytes();
                DatagramPacket pack = new DatagramPacket(data, data.length, clientAddress[0], clientPort[0]);
                sock.send(pack);
            } else 
                System.out.println("Nikto ne prishel na shodku podpischikov...");

        }
    }

}
