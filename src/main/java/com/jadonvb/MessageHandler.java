package com.jadonvb;

import com.google.gson.Gson;

public class MessageHandler {

    public MessageHandler() {

    }

    public void getMessage(String msg) {
        Gson gson = new Gson();
        Message message = gson.fromJson(msg, Message.class);

        System.out.println(message.type);
        System.out.println(message.getArguments().get(0));
    }
}
