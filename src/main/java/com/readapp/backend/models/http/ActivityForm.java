package com.readapp.backend.models.http;

import com.readapp.backend.models.*;

public class ActivityForm {
    private String type;
    private Long toUser;
    private Like like;
    private Comment comment;
    private Rate rate;
    private Reply reply;
    private User follower;


    public Rate getRate() {
        return rate;
    }

    public ActivityForm setRate(Rate rate) {
        this.rate = rate;
        return this;
    }

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
