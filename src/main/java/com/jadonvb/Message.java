package com.jadonvb;

import java.util.ArrayList;

public class Message {

    MessageTypes type;
    ArrayList<String> arguments;

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
