package com.readapp.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_tags", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<User> users;

    @Column(name = "content")
    private String content;

    public String getId() {
        return id;
    }

    public Tag setId(String id) {
        this.id = id;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Tag setUsers(List<User> users) {
        this.users = users;
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
