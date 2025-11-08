package com.chat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class Server 
{
	private final int port;                     // Port number for the server
    private final List<ClientHandler> clients;  // List of connected clients

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<ClientHandler>();
    }

    /**
     * Start listening for client connections.
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER] Listening on port " + port);

            // Infinite loop: accept incoming connections forever
            while (true) {
                Socket socket = serverSocket.accept(); // Wait for a client to connect
                System.out.println("[SERVER] Client connected from "
                        + socket.getInetAddress() + ":" + socket.getPort());

                // Create a new thread to handle this client
                ClientHandler handler = new ClientHandler(socket, this);

                // Add client to the active client list
                synchronized (clients) {
                    clients.add(handler);
                }

                // Start the thread for this client
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Server exception: " + e.getMessage());
        }
    }

    /**
     * Broadcasts a message to all connected clients except the sender.
     */
    public void broadcast(String message, ClientHandler exclude) {
        synchronized (clients) {
            for (ClientHandler c : new ArrayList<ClientHandler>(clients)) {
                if (c != exclude) {
                    c.sendMessage(message);
                }
            }
        }
    }

    /**
     * Removes a client when it disconnects.
     */
    public void removeClient(ClientHandler client) {
        synchronized (clients) {
            clients.remove(client);
        }
    }
}
