package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "recent_activities")
public class RecentActivity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private User fromUser;

    @OneToOne
    private Like fromLike;

    @OneToOne
    private Comment fromComment;

    @OneToOne
    private Article fromArticle;

    @ManyToOne
    private User toUser;

    @OneToOne
    private Rate fromRate;

    @OneToMany(mappedBy = "toRecentActivity")
    private List<Like> likes;

    @Column(name = "like_count")
    private Integer likeCount = 0;

    @OneToMany(mappedBy = "toRecentActivity", fetch = FetchType.EAGER)
    private List<Reply> replies;

    @OneToMany(mappedBy = "fromRecentActivity")
    private List<Activity> activities;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public List<Activity> getActivities() {
        return activities;
    }

    public RecentActivity setActivities(List<Activity> activities) {
        this.activities = activities;
        return this;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public RecentActivity setLikes(List<Like> likes) {
        this.likes = likes;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public RecentActivity setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RecentActivity setUser(User user) {
        this.user = user;
        return this;
    }

    public Rate getFromRate() {
        return fromRate;
    }

    public RecentActivity setFromRate(Rate fromRate) {
        this.fromRate = fromRate;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RecentActivity setId(Long id) {
        this.id = id;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public RecentActivity setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Like getFromLike() {
        return fromLike;
    }

    public RecentActivity setFromLike(Like fromLike) {
        this.fromLike = fromLike;
        return this;
    }

    public Comment getFromComment() {
        return fromComment;
    }

    public RecentActivity setFromComment(Comment fromComment) {
        this.fromComment = fromComment;
        return this;
    }

    public Article getFromArticle() {
        return fromArticle;
    }

    public RecentActivity setFromArticle(Article fromArticle) {
        this.fromArticle = fromArticle;
        return this;
    }

    public User getToUser() {
        return toUser;
    }

    public RecentActivity setToUser(User toUser) {
        this.toUser = toUser;
        return this;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public RecentActivity setReplies(List<Reply> replies) {
        this.replies = replies;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public RecentActivity setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public RecentActivity setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
