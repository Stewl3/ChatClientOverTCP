package com.stewie;

import java.net.*;
import java.io.*;

public class Server {
    private DataInputStream clientIn;
    private PrintWriter serverOut;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public static void main(String[] args) throws IOException {
        var server = new Server();
        server.serverStart(5000);
        server.messageHandler();
        server.serverStop();
    }


    public void serverStart(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server Started");
        System.out.println("Waiting for clients... ");
        clientSocket = serverSocket.accept();
        System.out.println("Connected");

        clientIn = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void messageHandler() throws IOException {
        String line = "";

        while(true) {
            line = clientIn.readUTF();
            System.out.println(line);

            serverOut.println(line);
        }
    }
    private void serverStop() throws IOException {
        System.out.println("Closing Connection");
        clientSocket.close();
        clientIn.close();
    }
}
