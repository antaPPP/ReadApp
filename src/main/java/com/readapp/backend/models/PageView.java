package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "page_views")
public class PageView {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private Article toArticle;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public PageView setId(Long id) {
        this.id = id;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public PageView setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Article getToArticle() {
        return toArticle;
    }

    public PageView setToArticle(Article toArticle) {
        this.toArticle = toArticle;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public PageView setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public PageView setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
