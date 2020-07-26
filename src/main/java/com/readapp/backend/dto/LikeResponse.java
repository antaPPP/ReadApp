package com.readapp.backend.dto;

import com.readapp.backend.models.Like;
import java.sql.Timestamp;

public class LikeResponse {

    private String id;

    private UserResponse fromUser;

    private ArticleResponse toArticle;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public LikeResponse(Like like){
        id = String.valueOf(like.getId());
        fromUser =  new UserResponse(like.getFromUser());
        toArticle = new ArticleResponse(like.getToArticle()).setContent(null);
        createdAt = like.getCreatedAt();
        updatedAt = like.getUpdatedAt();
    }

    public LikeResponse(){}

    public String getId() {
        return id;
    }

    public LikeResponse setId(String id) {
        this.id = id;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public LikeResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public ArticleResponse getToArticle() {
        return toArticle;
    }

    public LikeResponse setToArticle(ArticleResponse toArticle) {
        this.toArticle = toArticle;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public LikeResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public LikeResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
