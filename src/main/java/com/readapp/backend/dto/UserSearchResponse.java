package com.readapp.backend.dto;

import com.readapp.backend.models.User;

public class UserSearchResponse {

    private String id;

    private ProfileResponse profile;

    private int likeCount;

    private int articleCount;

    private boolean followed;

    public UserSearchResponse(){}

    public UserSearchResponse(User user) {
        this.id = String.valueOf(user.getId());
        this.profile = new ProfileResponse(user.getProfile());
    }

    public int getLikeCount() {
        return likeCount;
    }

    public UserSearchResponse setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public UserSearchResponse setArticleCount(int articleCount) {
        this.articleCount = articleCount;
        return this;
    }

    public boolean isFollowed() {
        return followed;
    }

    public UserSearchResponse setFollowed(boolean followed) {
        this.followed = followed;
        return this;
    }

    public String getId() {
        return id;
    }

    public UserSearchResponse setId(String id) {
        this.id = id;
        return this;
    }

    public ProfileResponse getProfile() {
        return profile;
    }

    public UserSearchResponse setProfile(ProfileResponse profile) {
        this.profile = profile;
        return this;
    }
}
