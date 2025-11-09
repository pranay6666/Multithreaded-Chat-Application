# 💬 Multi-Threaded Chat Application

## 🧩 Overview
A real-time **Java console-based chat system** built using **Core Java (Threads, Sockets, and Networking)**.  
This project allows multiple clients to connect to a central server and chat simultaneously.  
It demonstrates **multi-threading**, **client-server communication**, and **real-time message broadcasting** using sockets.

---

## 💻 Technologies Used
- Core Java (Threads, Sockets, Exception Handling)
- Java Networking (Socket, ServerSocket)
- Object-Oriented Programming (OOP)
- Console-based User Interface (Scanner, BufferedReader, PrintWriter)

---

## ⚙️ How to Run This Project (Eclipse)

### 1️⃣ Prerequisites
- **Java JDK 8+** installed  
  Check using:
  ```bash
  java -version
  javac -version
2️⃣ Create or Import the Project in Eclipse
Option A — Import existing project

Open Eclipse.

Go to File → Import → Existing Projects into Workspace.

Select the root folder of your project (e.g., MultiThreadedChatApplication).

Click Finish to import it into Eclipse.

Option B — Create manually

Go to File → New → Java Project → Name: MultiThreadedChatApplication → Finish.

Right-click src → New → Package → Name it com.chat.

Inside the com.chat package, create the following Java files:

ServerMain.java

Server.java

ClientHandler.java

Client.java

ReadThread.java

WriteThread.java

Paste the respective class code into each file.

3️⃣ Run the Server (in Eclipse)

Open the file ServerMain.java in Eclipse.

Right-click inside the editor → Run As → Java Application.

You’ll see:

==============================================
          Multi-Threaded Chat Server
==============================================
[SERVER] Listening on port 1238


Keep this console window running — it’s your chat server.

4️⃣ Run the Client (in Eclipse)

Open the file Client.java.

Right-click → Run As → Java Application.

When prompted:

Enter Server IP: type localhost (if running on same computer).

Enter your username (e.g., alice, bob).

You’ll see:

[INFO] Connected to chat server.
--- You can start chatting ---
Type 'exit' to leave.


Open another Eclipse console (Run → Run Configurations → new Java Application → select Client.java) to start another client.

Both clients can now chat in real time through the same server.

💡 Example Output
🖥️ Server Console
==============================================
          Multi-Threaded Chat Server
==============================================
[SERVER] Listening on port 1238
[JOIN] alice joined.
[JOIN] bob joined.
[alice]: Hello everyone!
[bob]: Hi Alice!
[LEFT] bob left.

💻 Client Console
==============================================
              Chat Client Console
==============================================
[INFO] Connected to chat server.
Enter your username: alice
--- You can start chatting ---
Type 'exit' to leave.

[JOIN] bob has joined the chat!
[bob]: Hi Alice!
[INFO] You left the chat.
