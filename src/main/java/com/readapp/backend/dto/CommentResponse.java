package com.readapp.backend.dto;

import com.readapp.backend.models.Comment;

import java.sql.Timestamp;
import java.util.List;

public class CommentResponse {
    private String id;
    private UserResponse fromUser;
    private List<ReplyResponse> replies;
    private Integer replyCount;
    private Integer likeCount;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CommentResponse(){}

    public CommentResponse(Comment comment) {

    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public CommentResponse setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public CommentResponse setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public String getId() {
        return id;
    }

    public CommentResponse setId(String id) {
        this.id = id;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public CommentResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public List<ReplyResponse> getReplies() {
        return replies;
    }

    public CommentResponse setReplies(List<ReplyResponse> replies) {
        this.replies = replies;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public CommentResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public CommentResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
