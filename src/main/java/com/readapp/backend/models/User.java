package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "permissions")
    private String permissions; // format: p1,p2,p3

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private Profile profile;

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "users")
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<Article> articles;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public User(){}

    public User(String password, String countryCode, String mobile, boolean blocked, String permissions, Profile profile, Timestamp createdAt, Timestamp updatedAt) {
        this.password = password;
        this.countryCode = countryCode;
        this.mobile = mobile;
        this.blocked = blocked;
        this.permissions = permissions;
        this.profile = profile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public User setArticles(List<Article> articles) {
        this.articles = articles;
        return this;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public User setBlocked(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public User setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public User setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public User setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public User setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPermissions() {
        return permissions;
    }

    public User setPermissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public User setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public User setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
