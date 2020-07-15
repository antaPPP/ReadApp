package com.readapp.backend.models.http;

import com.readapp.backend.dto.CommentResponse;
import com.readapp.backend.dto.LikeResponse;
import com.readapp.backend.dto.ReplyResponse;
import com.readapp.backend.dto.UserResponse;

import java.sql.Timestamp;

public class ActivityForm {
    private String type;
    private LikeResponse fromLike;
    private CommentResponse fromComment;
    private ReplyResponse fromReply;
    private UserResponse fromFollower;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ActivityForm() {

    }

    public String getType() {
        return type;
    }

    public ActivityForm setType(String type) {
        this.type = type;
        return this;
    }

    public LikeResponse getFromLike() {
        return fromLike;
    }

    public ActivityForm setFromLike(LikeResponse fromLike) {
        this.fromLike = fromLike;
        return this;
    }

    public CommentResponse getFromComment() {
        return fromComment;
    }

    public ActivityForm setFromComment(CommentResponse fromComment) {
        this.fromComment = fromComment;
        return this;
    }

    public ReplyResponse getFromReply() {
        return fromReply;
    }

    public ActivityForm setFromReply(ReplyResponse fromReply) {
        this.fromReply = fromReply;
        return this;
    }

    public UserResponse getFromFollower() {
        return fromFollower;
    }

    public ActivityForm setFromFollower(UserResponse fromFollower) {
        this.fromFollower = fromFollower;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public ActivityForm setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public ActivityForm setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
