package com.chat;

public class ServerMain 
{
	public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("          Multi-Threaded Chat Server");
        System.out.println("==============================================");

        // Create server on port 1238 and start listening for clients
        Server server = new Server(1238);
        server.start();
    }
}
