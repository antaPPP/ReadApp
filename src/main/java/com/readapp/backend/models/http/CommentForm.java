package com.readapp.backend.models.http;

import java.util.List;

public class CommentForm {
    private Long fromUser;
    private Long toArticle;
    private String content;
    private List<String> imageUrls;

    public CommentForm() {

    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public CommentForm setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public CommentForm setFromUser(Long fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Long getToArticle() {
        return toArticle;
    }

    public CommentForm setToArticle(Long toArticle) {
        this.toArticle = toArticle;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentForm setContent(String content) {
        this.content = content;
        return this;
    }
}
