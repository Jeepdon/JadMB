package com.jadonvb;

import com.google.gson.Gson;

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
    private ServerType serverType;
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
            Message message = new Message();
            message.setSender("JadMB");
            ArrayList<String> arguments = new ArrayList<>();
            arguments.add("hoihoi");
            message.setArguments(arguments);
            message.setType(MessageTypes.INFO);
            sendMessage(message);
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

    public void sendMessage(Message message) {
        Gson gson = new Gson();
        out.println(gson.toJson(message));
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
