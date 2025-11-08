package com.chat;

public class ClientMain
{
	public static void main(String[] args) {
        // Connect to localhost server on port 1238
        new Client("localhost", 1238).start();
    }
}
