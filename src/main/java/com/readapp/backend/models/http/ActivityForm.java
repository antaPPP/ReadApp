package com.readapp.backend.models.http;

import com.readapp.backend.models.Comment;
import com.readapp.backend.models.Like;
import com.readapp.backend.models.Reply;
import com.readapp.backend.models.User;

public class ActivityForm {
    private String type;
    private Long toUser;
    private Like like;
    private Comment comment;
    private Reply reply;
    private User follower;

    public Long getToUser() {
        return toUser;
    }

    public ActivityForm setToUser(Long toUser) {
        this.toUser = toUser;
        return this;
    }

    public String getType() {
        return type;
    }

    public ActivityForm setType(String type) {
        this.type = type;
        return this;
    }

    public Like getLike() {
        return like;
    }

    public ActivityForm setLike(Like like) {
        this.like = like;
        return this;
    }

    public Comment getComment() {
        return comment;
    }

    public ActivityForm setComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public Reply getReply() {
        return reply;
    }

    public ActivityForm setReply(Reply reply) {
        this.reply = reply;
        return this;
    }

    public User getFollower() {
        return follower;
    }

    public ActivityForm setFollower(User follower) {
        this.follower = follower;
        return this;
    }
}
