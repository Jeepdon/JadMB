package com.jadonvb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread {

    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;
    private String clientName;
    private final Server server;
    private ArrayList<String> subscribedQueues;
    private Logger logger;

    public Client(Socket clientSocket, Server server) throws IOException {

        logger = new Logger("JadMB");

        this.clientSocket = clientSocket;
        this.server = server;
        subscribedQueues = new ArrayList<>();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String name = in.readLine();

        Client client = server.getClient(name);

        if (client != null) {
            client.setSocket(clientSocket);
        } else {
            clientName = name;
            server.addClient(this);
            sendMessage("Successfully connected!");
            start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                server.getMessageHandler().getMessage(in.readLine());
            } catch (IOException ignored) {
                logger.log("Client " + clientName + " disconnected!");
                server.removeClient(this);
                break;
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getClientName() {
        return clientName;
    }

    public void setSocket(Socket newSocket) {
        clientSocket = newSocket;
    }

    public ArrayList<String> getSubscribedQueues() {
        return subscribedQueues;
    }
}
