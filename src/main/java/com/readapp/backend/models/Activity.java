package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Activity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;    // like, comment, follower, rate

    @ManyToOne
    private User user;

    @ManyToOne
    private User fromUser;

    @OneToOne
    private Like fromLike;

    @OneToOne
    private Comment fromComment;

    @OneToOne
    private Reply fromReply;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public User getUser() {
        return user;
    }

    public Activity setUser(User user) {
        this.user = user;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Activity setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Activity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Activity setType(String type) {
        this.type = type;
        return this;
    }

    public Like getFromLike() {
        return fromLike;
    }

    public Activity setFromLike(Like fromLike) {
        this.fromLike = fromLike;
        return this;
    }

    public Comment getFromComment() {
        return fromComment;
    }

    public Activity setFromComment(Comment fromComment) {
        this.fromComment = fromComment;
        return this;
    }

    public Reply getFromReply() {
        return fromReply;
    }

    public Activity setFromReply(Reply fromReply) {
        this.fromReply = fromReply;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Activity setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Activity setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
