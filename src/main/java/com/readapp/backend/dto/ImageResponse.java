package com.readapp.backend.dto;

import com.readapp.backend.models.Image;

public class ImageResponse {
    private Long id;
    private String imageUrl;
    private Double width;
    private Double height;

    public ImageResponse(Image image) {
        id = image.getId();
        imageUrl = image.getImageUrl();
        width = image.getWidth();
        height = image.getHeight();
    }

    public ImageResponse(){}

    public Long getId() {
        return id;
    }

    public ImageResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImageResponse setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public ImageResponse setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public ImageResponse setHeight(Double height) {
        this.height = height;
        return this;
    }
}
