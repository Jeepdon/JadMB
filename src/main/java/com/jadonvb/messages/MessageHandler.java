package com.jadonvb.messages;

import com.google.gson.Gson;
import com.jadonvb.enums.ServerType;
import com.jadonvb.instances.Client;
import com.jadonvb.Logger;
import com.jadonvb.instances.Server;
import com.jadonvb.enums.MessageTypes;

import java.util.ArrayList;

public class MessageHandler {

    private final Server server;
    private Client client;
    private final Logger logger;

    public MessageHandler(Server server, Client client) {
        this.server = server;
        this.client = client;
        logger = new Logger("JadMB");
    }

    public void getMessage(String msg) {

        Gson gson = new Gson();
        Message message = gson.fromJson(msg, Message.class);

        if (message == null) {
            if (msg != null) {
                logger.error("Message: " + msg);
            }
            return;
        }
        if (message.getReceiver() == null) {
            logger.log("No receiver named!");
            return;
        }

        log(message);

        if (message.getReceiver().equals("MB")) {

            if (message.getType().equals(MessageTypes.UNREGISTER)) unregister();

            if (!message.getType().equals(MessageTypes.INITIAL_MESSAGE)) {
                return;
            }

            if (message.getArguments().get(0) == null) {
                return;
            }

            client.setClientName(message.getArguments().get(0));
            client.setServerType(ServerType.valueOf(message.getArguments().get(1)));
            client.initiateClient();

            return;
        }

        Client receiver = server.getClient(message.getReceiver());

        if (receiver == null) {
            logger.log("Could not find client!");
            client.sendMessage(getClientNotFoundMessage(message));
            return;
        }

        receiver.sendMessage(message);

    }

    private void unregister() {
        if (client.getServerType().equals(ServerType.GAME)) {

            Client velocity = server.getClient("velocity");
            if (velocity == null) {
                return;
            }

            Message message = new Message();
            message.setSender(client.getClientName());
            message.setReceiver("velocity");
            message.setType(MessageTypes.UNREGISTER);

            velocity.sendMessage(message);
        }

        server.removeClient(client);
    }

    private void log(Message message) {
        logger.log(" ");
        logger.log("New message: ");
        logger.log("Sender: " + message.getSender());
        logger.log("Receiver: " + message.getReceiver());
        logger.log(message.getType().toString());
        try  {
            logger.log(message.getArguments().get(0));
        } catch (Exception ignore){}
    }

    private Message getClientNotFoundMessage(Message message) {
        Message newMessage = new Message();
        newMessage.setType(MessageTypes.CLIENT_NOT_FOUND);
        newMessage.setSender("MB");
        newMessage.setReceiver(client.getClientName());
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add(message.getReceiver());
        newMessage.setArguments(arguments);

        return newMessage;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
