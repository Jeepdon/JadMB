package com.jadonvb;

import java.util.ArrayList;

public class Message {

    String sender;
    String receiver;
    MessageTypes type;
    ArrayList<String> arguments;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setArguments(ArrayList<String> message) {
        this.arguments = message;
    }

    public void setType(MessageTypes type) {
        this.type = type;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public MessageTypes getType() {
        return type;
    }
}
