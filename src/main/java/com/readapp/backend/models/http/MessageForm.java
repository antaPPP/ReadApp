package com.readapp.backend.models.http;

public class MessageForm {
    private String type;
    private String content;
    private Long toChat;
    private Long fromUser;
    private Long toUser;

    public Long getToUser() {
        return toUser;
    }

    public MessageForm setToUser(Long toUser) {
        this.toUser = toUser;
        return this;
    }

    public Long getToChat() {
        return toChat;
    }

    public MessageForm setToChat(Long toChat) {
        this.toChat = toChat;
        return this;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public MessageForm setFromUser(Long fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getType() {
        return type;
    }

    public MessageForm setType(String type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageForm setContent(String content) {
        this.content = content;
        return this;
    }

}
