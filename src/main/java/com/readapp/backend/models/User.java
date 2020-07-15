package com.readapp.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Entity
@JsonIgnoreProperties(value = {"receivedMessages", "chats", "allChats"})
public class User implements Serializable {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<Article> articles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<Reply> replies;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "members")
    private List<Chat> chats;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "allMembers")
    private List<Chat> allChats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<Message> sentMessages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toUser")
    private List<Message> receivedMessages;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "follows")
    private List<User> followers;

    @ManyToMany
    private List<User> follows;

    @OneToMany(mappedBy = "fromUser")
    private List<Activity> followActivities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;


    @Column(name = "likeCount")
    private int likeCount;

    @Column(name = "follower_count")
    private int followerCount;

    @Column(name = "follow_count")
    private int followCount;

    @Column(name = "article_count")
    private int articleCount;

    @Column(name = "avg_rate_score")
    private double avgRateScore;

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

    public List<Activity> getFollowActivities() {
        return followActivities;
    }

    public User setFollowActivities(List<Activity> followActivities) {
        this.followActivities = followActivities;
        return this;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public User setActivities(List<Activity> activities) {
        this.activities = activities;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public User setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public User setReplies(List<Reply> replies) {
        this.replies = replies;
        return this;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public User setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public User setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
        return this;
    }

    public int getFollowCount() {
        return followCount;
    }

    public User setFollowCount(int followCount) {
        this.followCount = followCount;
        return this;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public User setArticleCount(int articleCount) {
        this.articleCount = articleCount;
        return this;
    }

    public double getAvgRateScore() {
        return avgRateScore;
    }

    public User setAvgRateScore(double avgRateScore) {
        this.avgRateScore = avgRateScore;
        return this;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public User setFollowers(List<User> followers) {
        this.followers = followers;
        return this;
    }

    public List<User> getFollows() {
        return follows;
    }

    public User setFollows(List<User> follows) {
        this.follows = follows;
        return this;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public User setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
        return this;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public User setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
        return this;
    }

    public List<Chat> getAllChats() {
        return allChats;
    }

    public User setAllChats(List<Chat> allChats) {
        this.allChats = allChats;
        return this;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public User setChats(List<Chat> chats) {
        this.chats = chats;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        User user = (User) o;
        return user.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, countryCode, mobile, blocked, permissions, profile, tags, articles, chats, allChats, sentMessages, receivedMessages, createdAt, updatedAt);
    }
}
