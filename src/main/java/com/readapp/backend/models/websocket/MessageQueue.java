package com.readapp.backend.models.websocket;

import com.readapp.backend.models.Message;

import java.util.Queue;

public class MessageQueue {

    Queue<Message> unreadMessages;

    public Queue<Message> getUnreadMessages() {
        return unreadMessages;
    }

    public MessageQueue setUnreadMessages(Queue<Message> unreadMessages) {
        this.unreadMessages = unreadMessages;
        return this;
    }
}
