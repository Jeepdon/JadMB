package com.jadonvb;

import com.google.gson.Gson;

public class MessageHandler {

    private Server server;
    private Logger logger;

    public MessageHandler(Server server) {
        this.server = server;
        logger = new Logger("JadMB");
    }

    public void getMessage(String msg) {

        Gson gson = new Gson();
        Message message = gson.fromJson(msg, Message.class);

        if (message.getReceiver() == null) {
            logger.log("No receiver named!");
            return;
        }

        log(message);

        if (message.getReceiver().equals("MQ")) {
            return;
        }

        Client client = server.getClient(message.getReceiver());

        if (client == null) {
            logger.log("Could not find client!");
            return;
        }

        client.sendMessage(message);

    }

    private void log(Message message) {
        logger.log("New message: ");
        logger.log("Sender: " + message.getSender());
        logger.log("Receiver: " + message.getReceiver());
        logger.log(message.getType().toString());
        logger.log(message.getArguments().get(0));
    }
}
