package com.readapp.backend.models.http;

public class ReplyForm {
    private Long fromUser;
    private String content;
    private Long toReply;
    private Long toComment;

    public ReplyForm(){

    }

    public Long getFromUser() {
        return fromUser;
    }

    public ReplyForm setFromUser(Long fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ReplyForm setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getToReply() {
        return toReply;
    }

    public ReplyForm setToReply(Long toReply) {
        this.toReply = toReply;
        return this;
    }

    public Long getToComment() {
        return toComment;
    }

    public ReplyForm setToComment(Long toComment) {
        this.toComment = toComment;
        return this;
    }
}
