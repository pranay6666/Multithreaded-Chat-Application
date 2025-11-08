package com.chat;
import java.io.*;
import java.net.Socket;
public class ClientHandler extends Thread
{
	private final Socket socket;   // The socket for communication with this client
    private final Server server;   // Reference to the main server
    private PrintWriter writer;    // Used to send data to this client
    private String username;       // Username of this client

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        BufferedReader reader = null;
        try {
            // Input and output streams for communication
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // Wait for username from client
            System.out.println("[DEBUG] Waiting for username from " + socket.getPort());
            username = reader.readLine();
            System.out.println("[DEBUG] Received username: " + username + " from " + socket.getPort());

            // Validate username
            if (username == null || username.trim().isEmpty()) {
                System.out.println("[SERVER] Invalid username — closing connection.");
                closeResources();
                return;
            }

            // Notify all that this user joined
            System.out.println("[JOIN] " + username + " joined.");
            server.broadcast("[JOIN] " + username + " joined the chat.", this);

            // Continuously read messages from this client
            String msg;
            while ((msg = reader.readLine()) != null) {
                // If user types "exit", break out of the loop
                if (msg.equalsIgnoreCase("exit")) {
                    break;
                }

                // Format and broadcast message
                String formatted = "[" + username + "]: " + msg;
                System.out.println(formatted); // Server log
                server.broadcast(formatted, this);
            }

        } catch (IOException e) {
            System.out.println("[ERROR] Connection error with " + username + ": " + e.getMessage());
        } finally {
            // When client disconnects
            server.removeClient(this);
            server.broadcast("[LEFT] " + username + " left the chat.", this);
            System.out.println("[LEFT] " + username + " left.");
            closeResources();
        }
    }

    /** Sends a message to this client. */
    public void sendMessage(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }

    /** Safely closes socket and writer resources. */
    private void closeResources() {
        try {
            if (writer != null) writer.close();
        } catch (Exception ignored) {}
        try {
            if (!socket.isClosed()) socket.close();
        } catch (Exception ignored) {}
    }
}
