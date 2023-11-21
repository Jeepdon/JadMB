package com.jadonvb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageQueue {

    // Queue name, messages
    private final HashMap<String, ArrayList<String>> messageQueues;

    public MessageQueue() {
        messageQueues = new HashMap<>();
    }

    public void addMessage(String message, String queue) {
        if (messageQueues.containsKey(queue)) {
            messageQueues.get(queue).add(message);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(message);
            messageQueues.put(queue, list);
        }
    }
}
