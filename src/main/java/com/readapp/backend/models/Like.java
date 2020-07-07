package com.readapp.backend.models;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "likes")
@EntityListeners(AuditingEntityListener.class)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private Article toArticle;

    @ManyToOne
    private Comment toComment;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Comment getToComment() {
        return toComment;
    }

    public Like setToComment(Comment toComment) {
        this.toComment = toComment;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Like setId(Long id) {
        this.id = id;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Like setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Article getToArticle() {
        return toArticle;
    }

    public Like setToArticle(Article toArticle) {
        this.toArticle = toArticle;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Like setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Like setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
