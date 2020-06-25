package com.readapp.backend.dto;

import java.sql.Timestamp;

public class MessageResponse {
    private Long id;
    private String type;
    private UserResponse fromUser;
    private String content;
    private ChatResponse chat;
    private ImageResponse image;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public MessageResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public MessageResponse setType(String type) {
        this.type = type;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public MessageResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public ChatResponse getChat() {
        return chat;
    }

    public MessageResponse setChat(ChatResponse chat) {
        this.chat = chat;
        return this;
    }

    public ImageResponse getImage() {
        return image;
    }

    public MessageResponse setImage(ImageResponse image) {
        this.image = image;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public MessageResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public MessageResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
