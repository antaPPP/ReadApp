package com.readapp.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User fromUser;

    @OneToOne(mappedBy = "fromArticle")
    private RecentActivity recentActivity;

    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "excerpt")
    private String excerpt;

    @Column(name = "coverUrl")
    private String coverUrl;

    @Column(name = "content", columnDefinition = "text")
    @Lob
    private String content;

    @Column(name = "comment_count")
    private Integer commentCount;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "rate_score")
    private Double rateScore;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toArticle")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toArticle")
    private List<Rate> rates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toArticle")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toArticle")
    private List<PageView> pageViews;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public RecentActivity getRecentActivity() {
        return recentActivity;
    }

    public Article setRecentActivity(RecentActivity recentActivity) {
        this.recentActivity = recentActivity;
        return this;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Article setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public List<PageView> getPageViews() {
        return pageViews;
    }

    public Article setPageViews(List<PageView> pageViews) {
        this.pageViews = pageViews;
        return this;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public Article setRates(List<Rate> rates) {
        this.rates = rates;
        return this;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public Article setLikes(List<Like> likes) {
        this.likes = likes;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Article setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Article setFromUser(User fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Article setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public Article setExcerpt(String excerpt) {
        this.excerpt = excerpt;
        return this;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public Article setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Article setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Article setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public Double getRateScore() {
        return rateScore;
    }

    public Article setRateScore(Double rateScore) {
        this.rateScore = rateScore;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Article setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Article setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
