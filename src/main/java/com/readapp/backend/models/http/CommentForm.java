package com.readapp.backend.models.http;

public class CommentForm {
    private Long fromUser;
    private Long toArticle;
    private String content;

    public CommentForm() {
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
