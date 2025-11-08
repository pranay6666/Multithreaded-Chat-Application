package com.chat;
import java.io.*;
import java.net.Socket;
public class ReadThread extends Thread
{
	private final BufferedReader reader; // Input stream from server

    public ReadThread(BufferedReader reader) {
        this.reader = reader;
    }

    public void run() {
        try {
            String response;
            // Continuously read and display messages from server
            while ((response = reader.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException e) {
            System.out.println("[INFO] Disconnected from server.");
        } finally {
            try { reader.close(); } catch (IOException ignored) {}
        }
    }
}
