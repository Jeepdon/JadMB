package com.jadonvb;

import com.google.gson.Gson;
import com.jadonvb.util.Logger;

public class MessageHandler {

    public MessageHandler() {

    }

    public void getMessage(String msg) {
        Gson gson = new Gson();
        Message message = gson.fromJson(msg, Message.class);

        Logger logger = new Logger();

        logger.debug(String.valueOf(message.type));
        logger.debug(message.getSender());
        logger.debug(message.getArguments().get(0));

    }
}
