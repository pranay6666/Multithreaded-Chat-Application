package com.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class WriteThread extends Thread
{
	private final Socket socket;     // Socket for communication
    private final PrintWriter writer;// Output stream (shared from Client)
    private final Scanner scanner;   // For reading console input
    private final String username;   // User's name for reference

    public WriteThread(Socket socket, PrintWriter writer, String username) {
        this.socket = socket;
        this.writer = writer;
        this.username = username;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        try {
            System.out.println("--- You can start chatting ---");
            System.out.println("Type 'exit' to leave.\n");

            String message;
            // Keep reading user input until they type 'exit'
            while (true) {
                message = scanner.nextLine();
                writer.println(message); // Send message to server

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("[INFO] You left the chat.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Connection issue for " + username + ": " + e.getMessage());
        } finally {
            // Close socket once the user exits
            try {
                if (!socket.isClosed()) socket.close();
            } catch (IOException ignored) {}
        }
    }
}
