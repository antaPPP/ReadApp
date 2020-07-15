package com.readapp.backend.dto;

import com.readapp.backend.models.Article;

import java.sql.Timestamp;

public class ArticleResponse {
    private String id;
    private UserResponse fromUser;
    private String author;
    private String title;
    private String type;
    private String coverUrl;
    private String excerpt;
    private String content;
    private Integer likeCount;
    private Boolean liked = false;
    private Integer commentCount;
    private Double rateScore;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ArticleResponse(){}

    public ArticleResponse(Article article) {
        id = String.valueOf(article.getId());
        fromUser = new UserResponse(article.getFromUser());
        author = article.getAuthor();
        title = article.getTitle();
        excerpt = article.getExcerpt();
        coverUrl = article.getCoverUrl();
        content = article.getContent();
        likeCount = article.getLikeCount();
        commentCount = article.getCommentCount();
        rateScore = article.getRateScore();
        createdAt = article.getCreatedAt();
        updatedAt = article.getUpdatedAt();
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public ArticleResponse setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public ArticleResponse setExcerpt(String excerpt) {
        this.excerpt = excerpt;
        return this;
    }

    public ArticleResponse setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public Boolean getLiked() {
        return liked;
    }

    public ArticleResponse setLiked(Boolean liked) {
        this.liked = liked;
        return this;
    }

    public ArticleResponse setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public ArticleResponse setRateScore(Double rateScore) {
        this.rateScore = rateScore;
        return this;
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
