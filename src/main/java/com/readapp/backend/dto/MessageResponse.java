package com.readapp.backend.dto;

import com.readapp.backend.models.Message;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * JSON:
 * {
 *     id,
 *     type,
 *     fromUser: {
 *         id,
 *     },
 *     content,
 *     image,
 *     chat: {
 *         id
 *     },
 * }
 */
public class MessageResponse implements Serializable {
    private Long id;
    private ChatResponse fromChat;
    private String type;
    private UserResponse fromUser;
    private String content;
    private ImageResponse image;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MessageResponse(){}

    public MessageResponse(@NotNull Message message) {
        this.id = message.getId();
        this.type = message.getType();
        this.fromUser = new UserResponse(message.getFromUser());
        this.fromChat = new ChatResponse().setId(message.getChat().getId());
        this.content = message.getContent();
        if (message.getImage() != null) this.image = new ImageResponse(message.getImage());
        this.createdAt = message.getCreatedAt();
        this.updatedAt = message.getUpdatedAt();
    }

    public ChatResponse getFromChat() {
        return fromChat;
    }

    public MessageResponse setFromChat(ChatResponse fromChat) {
        this.fromChat = fromChat;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public MessageResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

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

    public String getContent() {
        return content;
    }

    public MessageResponse setContent(String content) {
        this.content = content;
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
