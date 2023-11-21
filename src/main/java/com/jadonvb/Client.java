package com.jadonvb;

import com.jadonvb.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;
    private String clientName;
    private Logger logger;
    private final Server server;

    public Client(Socket clientSocket, Server server) throws IOException {

        this.clientSocket = clientSocket;
        this.server = server;

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        logger = new Logger();

        String name = in.readLine();

        Client client = server.getClient(name);

        if (client != null) {
            client.setSocket(clientSocket);
        } else {
            clientName = name;
            server.addClient(this);
            start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                logger.log(in.readLine());
            } catch (IOException ignored) {
                logger.log("Client " + clientName + " disconnected!");
                server.removeClient(this);
                break;
            }
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setSocket(Socket newSocket) {
        clientSocket = newSocket;
    }
}