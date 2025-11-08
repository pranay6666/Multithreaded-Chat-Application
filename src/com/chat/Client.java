package com.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client 
{
	private final String host;  // Server IP address
    private final int port;     // Server port number

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        Socket socket = null;
        try {
            // Connect to server
            socket = new Socket(host, port);
            System.out.println("==============================================");
            System.out.println("              Chat Client Console");
            System.out.println("==============================================");
            System.out.println("[INFO] Connected to chat server.");

            // Setup input/output streams
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Get username from user and send to server
            System.out.print("Enter your username: ");
            String username = scanner.nextLine().trim();
            while (username.isEmpty()) {
                System.out.print("Username cannot be empty. Enter again: ");
                username = scanner.nextLine().trim();
            }
            out.println(username); // Send username to server

            // Start a thread to read messages from server
            ReadThread reader = new ReadThread(in);
            reader.start();

            // Start a thread to send user input messages to server
            WriteThread writer = new WriteThread(socket, out, username);
            writer.start();

            // Wait until writer thread finishes (when user exits)
            writer.join();

            // Close socket when done
            if (!socket.isClosed()) socket.close();

        } catch (IOException | InterruptedException e) {
            System.out.println("[ERROR] " + e.getMessage());
            try { if (socket != null && !socket.isClosed()) socket.close(); } catch (IOException ignored) {}
        }
    }
}
