package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rates")
@EntityListeners(AuditingEntityListener.class)
public class Rate {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User fromUser;

    @Column(name = "score", nullable = false)
    private Double score;

    @ManyToOne
    private Article toArticle;

    @OneToOne(mappedBy = "fromRate")
    private Activity activity;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Activity getActivity() {
        return activity;
    }

    public Rate setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Rate setId(Long id) {
        this.id = id;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Rate setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public Rate setScore(Double score) {
        this.score = score;
        return this;
    }

    public Article getToArticle() {
        return toArticle;
    }

    public Rate setToArticle(Article toArticle) {
        this.toArticle = toArticle;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Rate setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Rate setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
