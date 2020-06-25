package com.readapp.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tags")
@JsonIgnoreProperties(value = {"user"})
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;

    @Column(name = "content")
    private String content;

    public Long getId() {
        return id;
    }

    public Tag setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Tag setUser(User user) {
        this.user = user;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Tag setContent(String content) {
        this.content = content;
        return this;
    }
}
