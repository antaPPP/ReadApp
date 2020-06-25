package com.readapp.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
@JsonIgnoreProperties(value = {"message"})
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "width", nullable = false)
    private Double width;

    @OneToOne(fetch = FetchType.LAZY)
    private Message message;

    public Message getMessage() {
        return message;
    }

    public Image setMessage(Message message) {
        this.message = message;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Image setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Image setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public Image setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public Image setWidth(Double width) {
        this.width = width;
        return this;
    }
}
