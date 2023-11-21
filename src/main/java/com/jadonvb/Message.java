package com.jadonvb;

import java.util.ArrayList;

public class Message {

    String sender;
    MessageTypes type;
    ArrayList<String> arguments;
    boolean request;

    public void setRequest(boolean request) {
        this.request = request;
    }

    public boolean isRequest() {
        return request;
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
