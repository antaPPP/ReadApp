package com.readapp.backend.models.http;

public class ImageMessageForm {
    private String imageUrl;
    private Double width;
    private Double height;
    private Long fromUser;
    private Long toUser;

    public Long getToUser() {
        return toUser;
    }

    public ImageMessageForm setToUser(Long toUser) {
        this.toUser = toUser;
        return this;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public ImageMessageForm setFromUser(Long fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImageMessageForm setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public ImageMessageForm setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public ImageMessageForm setHeight(Double height) {
        this.height = height;
        return this;
    }
}
