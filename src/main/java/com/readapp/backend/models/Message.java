package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "content")
    private String content;

    @OneToOne
    private Image image;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;

    @ManyToOne
    private Chat chat;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Message setType(String type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Message setImage(Image image) {
        this.image = image;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Message setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public User getToUser() {
        return toUser;
    }

    public Message setToUser(User toUser) {
        this.toUser = toUser;
        return this;
    }

    public Chat getChat() {
        return chat;
    }

    public Message setChat(Chat chat) {
        this.chat = chat;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Message setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Message setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
