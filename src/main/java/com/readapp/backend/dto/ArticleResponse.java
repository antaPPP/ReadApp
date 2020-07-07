package com.readapp.backend.dto;

import com.readapp.backend.models.Article;

import java.sql.Timestamp;

public class ArticleResponse {
    private String id;
    private UserResponse fromUser;
    private String author;
    private String title;
    private String type;
    private String content;
    private Integer likeCount;
    private Integer commentCount;
    private Double rateScore;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ArticleResponse(){}

    public ArticleResponse(Article article) {

    }

    public String getType() {
        return type;
    }

    public ArticleResponse setType(String type) {
        this.type = type;
        return this;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public ArticleResponse setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public ArticleResponse setCommentCount(int commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public double getRateScore() {
        return rateScore;
    }

    public ArticleResponse setRateScore(double rateScore) {
        this.rateScore = rateScore;
        return this;
    }

    public String getId() {
        return id;
    }

    public ArticleResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleResponse setAuthor(String author) {
        this.author = author;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public ArticleResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public ArticleResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public ArticleResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
