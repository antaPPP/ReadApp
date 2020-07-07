package com.readapp.backend.dto;

import com.readapp.backend.models.Reply;

import java.sql.Timestamp;

public class ReplyResponse {
    private String id;
    private UserResponse fromUser;
    private ReplyResponse toReply;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ReplyResponse(){}

    public ReplyResponse(Reply reply) {

    }

    public String getId() {
        return id;
    }

    public ReplyResponse setId(String id) {
        this.id = id;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public ReplyResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public ReplyResponse getToReply() {
        return toReply;
    }

    public ReplyResponse setToReply(ReplyResponse toReply) {
        this.toReply = toReply;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ReplyResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public ReplyResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public ReplyResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
