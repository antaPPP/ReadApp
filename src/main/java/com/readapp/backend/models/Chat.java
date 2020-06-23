package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chats")
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;    // "Group" || "Direct"

    @ManyToMany
    private List<User> members;

    @ManyToMany
    private List<User> allMembers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<Message> messages;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public Chat setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Chat setType(String type) {
        this.type = type;
        return this;
    }

    public List<User> getMembers() {
        return members;
    }

    public Chat setMembers(List<User> members) {
        this.members = members;
        return this;
    }

    public List<User> getAllMembers() {
        return allMembers;
    }

    public Chat setAllMembers(List<User> allMembers) {
        this.allMembers = allMembers;
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Chat setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Chat setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Chat setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
