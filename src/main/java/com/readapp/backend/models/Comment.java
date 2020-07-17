package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "fromComment")
    private Activity activity;

    @ManyToOne
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article toArticle;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "toComment", cascade = CascadeType.ALL)
    private List<Reply> replies;

    @OneToMany(mappedBy = "toComment", cascade = CascadeType.ALL)
    private List<Like> likes;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "reply_count")
    private int replyCount;

    @Column(name = "rate")
    private Double rate;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Double getRate() {
        return rate;
    }

    public Comment setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public Activity getActivity() {
        return activity;
    }

    public Comment setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public Comment setLikes(List<Like> likes) {
        this.likes = likes;
        return this;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public Comment setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public Comment setReplyCount(int replyCount) {
        this.replyCount = replyCount;
        return this;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public Comment setReplies(List<Reply> replies) {
        this.replies = replies;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Comment setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Article getToArticle() {
        return toArticle;
    }

    public Comment setToArticle(Article toArticle) {
        this.toArticle = toArticle;
        return this;
    }



    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Comment setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Comment setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
