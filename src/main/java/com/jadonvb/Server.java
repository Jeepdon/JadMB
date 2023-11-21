package com.jadonvb;

import com.jadonvb.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private final Logger logger;
    private final int PORT = 6989;
    private ServerSocket serverSocket;
    private MessageHandler messageHandler;
    private MessageQueue messageQueue;
    private ArrayList<Client> clients;

    public Server() {

        logger = new Logger();

        clients = new ArrayList<>();

        messageHandler = new MessageHandler();
        messageQueue = new MessageQueue();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            logger.error("Could not listen on port " + PORT + "!");
            System.exit(1);
        }

        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Client(clientSocket,this);
            } catch (IOException e) {
                logger.error("Can't connect new client!");
                throw new RuntimeException(e);
            }
            logger.log("Amount of clients: " + clients.size());
        }
    }

    public Client getClient(String name) {
        for (Client client : clients) {
            if (client.getClientName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
