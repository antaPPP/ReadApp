package com.readapp.backend.dto;

import com.readapp.backend.models.Like;
import com.readapp.backend.models.RecentActivity;
import com.readapp.backend.models.Reply;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RecentActivityResponse {
    private String id;
    private UserResponse fromUser;
    private String type;
    private Integer likeCount;
    private boolean liked;
    private List<ReplyResponse> replies;
    private LikeResponse fromLike;
    private CommentResponse fromComment;
    private ArticleResponse fromArticle;
    private RateResponse fromRate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public RecentActivityResponse() {
    }

    public RecentActivityResponse(RecentActivity activity) {
        id = String.valueOf(activity.getId());
        fromUser = new UserResponse(activity.getFromUser());
        if (activity.getFromLike() != null) {
            type = "like";
            fromLike = new LikeResponse(activity.getFromLike());
        }
        if (activity.getFromComment() != null) {
            type = "comment";
            fromComment = new CommentResponse(activity.getFromComment());
        }
        if (activity.getFromArticle() != null) {
            type = "article";
            fromArticle = new ArticleResponse(activity.getFromArticle()).setContent(null);
        }
        if (activity.getFromRate() != null) {
            type = "rate";
            fromRate = new RateResponse(activity.getFromRate());
        }
        replies = new ArrayList<>();
        if (activity.getReplies() != null) {
            for (Reply reply : activity.getReplies()) {
                replies.add(new ReplyResponse(reply));
            }
        }
        likeCount = activity.getLikeCount();

        createdAt = activity.getCreatedAt();
        updatedAt = activity.getUpdatedAt();
    }

    public boolean isLiked() {
        return liked;
    }

    public RecentActivityResponse setLiked(boolean liked) {
        this.liked = liked;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public RecentActivityResponse setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public List<ReplyResponse> getReplies() {
        return replies;
    }

    public RecentActivityResponse setReplies(List<ReplyResponse> replies) {
        this.replies = replies;
        return this;
    }

    public String getType() {
        return type;
    }

    public RecentActivityResponse setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public RecentActivityResponse setId(String id) {
        this.id = id;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public RecentActivityResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public LikeResponse getFromLike() {
        return fromLike;
    }

    public RecentActivityResponse setFromLike(LikeResponse fromLike) {
        this.fromLike = fromLike;
        return this;
    }

    public CommentResponse getFromComment() {
        return fromComment;
    }

    public RecentActivityResponse setFromComment(CommentResponse fromComment) {
        this.fromComment = fromComment;
        return this;
    }

    public ArticleResponse getFromArticle() {
        return fromArticle;
    }

    public RecentActivityResponse setFromArticle(ArticleResponse fromArticle) {
        this.fromArticle = fromArticle;
        return this;
    }

    public RateResponse getFromRate() {
        return fromRate;
    }

    public RecentActivityResponse setFromRate(RateResponse fromRate) {
        this.fromRate = fromRate;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public RecentActivityResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public RecentActivityResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
