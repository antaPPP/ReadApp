package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reply")
@EntityListeners(AuditingEntityListener.class)
public class Reply {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private Comment toComment;

    @ManyToOne
    private Reply toReply;

    @OneToOne(mappedBy = "fromReply")
    private Activity activity;

    @UpdateTimestamp
    private Timestamp updatedAt;
    @CreationTimestamp
    private Timestamp createdAt;

    public Activity getActivity() {
        return activity;
    }

    public Reply setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Reply setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Reply setContent(String content) {
        this.content = content;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Reply setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public Comment getToComment() {
        return toComment;
    }

    public Reply setToComment(Comment toComment) {
        this.toComment = toComment;
        return this;
    }

    public Reply getToReply() {
        return toReply;
    }

    public Reply setToReply(Reply toReply) {
        this.toReply = toReply;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Reply setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Reply setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
