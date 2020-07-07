package com.readapp.backend.models.http;


public class ArticleForm {
    private Long fromUser;
    private String coverUrl;
    private String title;
    private String author;
    private String content;
    private String excerpt;

    public ArticleForm(){

    }

    public String getExcerpt() {
        return excerpt;
    }

    public ArticleForm setExcerpt(String excerpt) {
        this.excerpt = excerpt;
        return this;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public ArticleForm setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public ArticleForm setFromUser(Long fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleForm setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleForm setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleForm setContent(String content) {
        this.content = content;
        return this;
    }
}
