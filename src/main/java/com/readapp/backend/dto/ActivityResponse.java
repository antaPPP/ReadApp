package com.readapp.backend.dto;

import com.readapp.backend.models.Activity;
import java.sql.Timestamp;

public class ActivityResponse {
    private String id;
    private String type;
    private LikeResponse fromLike;
    private CommentResponse fromComment;
    private ReplyResponse fromReply;
    private UserResponse fromFollower;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ActivityResponse(){}

    public ActivityResponse(Activity activity) {
        id = String.valueOf(activity.getId());
        type = activity.getType();
        if (activity.getFromLike() != null) fromLike = new LikeResponse(activity.getFromLike());
        if (activity.getFromComment() != null) fromComment = new CommentResponse(activity.getFromComment());
        if (activity.getFromReply() != null) fromReply =  new ReplyResponse(activity.getFromReply());
        if (activity.getFromUser() != null) fromFollower = new UserResponse(activity.getFromUser());
        createdAt = activity.getCreatedAt();
        updatedAt = activity.getUpdatedAt();
    }

    public String getId() {
        return id;
    }

    public ActivityResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public ActivityResponse setType(String type) {
        this.type = type;
        return this;
    }

    public LikeResponse getFromLike() {
        return fromLike;
    }

    public ActivityResponse setFromLike(LikeResponse fromLike) {
        this.fromLike = fromLike;
        return this;
    }

    public CommentResponse getFromComment() {
        return fromComment;
    }

    public ActivityResponse setFromComment(CommentResponse fromComment) {
        this.fromComment = fromComment;
        return this;
    }

    public ReplyResponse getFromReply() {
        return fromReply;
    }

    public ActivityResponse setFromReply(ReplyResponse fromReply) {
        this.fromReply = fromReply;
        return this;
    }

    public UserResponse getFromFollower() {
        return fromFollower;
    }

    public ActivityResponse setFromFollower(UserResponse fromFollower) {
        this.fromFollower = fromFollower;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public ActivityResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public ActivityResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
