package com.readapp.backend.dto;

import com.alibaba.fastjson.JSONArray;
import com.readapp.backend.models.Comment;
import com.readapp.backend.models.Reply;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentResponse {
    private String id;
    private UserResponse fromUser;
    private ArticleResponse toArticle;
    private List<ReplyResponse> replies;
    private List<String> imageUrls;
    private Integer replyCount;
    private Integer likeCount;
    private String content;
    private Double score;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CommentResponse(){}

    public CommentResponse(Comment comment) {
        id = String.valueOf(comment.getId());
        fromUser = new UserResponse(comment.getFromUser());
        replyCount = comment.getReplyCount();
        likeCount = comment.getLikeCount();
        replies = new ArrayList<>();
        content = comment.getContent();
        createdAt = comment.getCreatedAt();
        updatedAt = comment.getUpdatedAt();
        if (comment.getPictureUrls() != null) {
            JSONArray jsonArray = JSONArray.parseArray(comment.getPictureUrls());
            this.imageUrls = new ArrayList<>();
            for (Object item : jsonArray) {
                imageUrls.add(item.toString());
            }
        }
        if (comment.getRate() != null) {
            score = comment.getRate();
        }
        if (comment.getToArticle() != null) {
            toArticle = new ArticleResponse(comment.getToArticle().setContent(null));
        }
        if (comment.getReplies() != null) {
            for (Reply reply : comment.getReplies()) {
                replies.add(new ReplyResponse(reply));
            }
        }
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public CommentResponse setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public CommentResponse setScore(Double score) {
        this.score = score;
        return this;
    }

    public ArticleResponse getToArticle() {
        return toArticle;
    }

    public CommentResponse setToArticle(ArticleResponse toArticle) {
        this.toArticle = toArticle;
        return this;
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
