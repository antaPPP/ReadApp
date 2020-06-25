package com.readapp.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "profile")
@EntityListeners(AuditingEntityListener.class)
@Entity
@JsonIgnoreProperties(value = {"user"})
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "whatsup")
    private String whatsup;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "cover_url")
    private String coverUrl;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public Profile setUser(User user) {
        this.user = user;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Profile setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getWhatsup() {
        return whatsup;
    }

    public Profile setWhatsup(String whatsup) {
        this.whatsup = whatsup;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Profile setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public Profile setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }
}
