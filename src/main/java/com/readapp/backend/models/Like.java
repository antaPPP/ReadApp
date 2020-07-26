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

    @OneToOne(mappedBy = "fromLike")
    private Activity activity;

    @OneToOne(mappedBy = "fromLike")
    private RecentActivity recentActivity;

    @ManyToOne
    private RecentActivity toRecentActivity;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private Article toArticle;

    @ManyToOne
    private Comment toComment;

    @Column(name = "disliked")
    private boolean dislike = true;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public RecentActivity getToRecentActivity() {
        return toRecentActivity;
    }

    public Like setToRecentActivity(RecentActivity toRecentActivity) {
        this.toRecentActivity = toRecentActivity;
        return this;
    }

    public boolean isDislike() {
        return dislike;
    }

    public RecentActivity getRecentActivity() {
        return recentActivity;
    }

    public Like setRecentActivity(RecentActivity recentActivity) {
        this.recentActivity = recentActivity;
        return this;
    }

    public Activity getActivity() {
        return activity;
    }

    public Like setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

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

    public boolean getDislike() { return dislike; }

    public Like setDislike(boolean dislike) {
        this.dislike = dislike;
        return this;
    }
}
