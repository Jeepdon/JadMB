package com.jadonvb;

import java.util.ArrayList;

public class Message {

    private String sender;
    private String receiver;
    private MessageTypes type;
    private ArrayList<String> arguments;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

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
