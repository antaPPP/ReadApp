package com.readapp.backend.dto;

import com.readapp.backend.models.User;

import java.util.Arrays;
import java.util.List;

public class UserResponse {

    private Long id;

    private int followerCount;

    private int followCount;

    private int articleCount;

    private int likeCount;

    private double avgRateScore;

    private List<String> permissions;

    private ProfileResponse profile;

    public UserResponse(User user){
        this.id = user.getId();
        if(user.getPermissions() != null) permissions = Arrays.asList(user.getPermissions().split("."));
        if(user.getProfile() != null) profile = new ProfileResponse(
                user.getProfile()
        );
        this.likeCount = user.getLikeCount();
        this.articleCount = user.getArticleCount();
        this.followerCount = user.getFollowerCount();
        this.followCount = user.getFollowCount();
        this.avgRateScore = user.getAvgRateScore();
    }

    public UserResponse(){}

    public Long getId() {
        return id;
    }

    public UserResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public UserResponse setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public ProfileResponse getProfile() {
        return profile;
    }

    public UserResponse setProfile(ProfileResponse profile) {
        this.profile = profile;
        return this;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public UserResponse setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
        return this;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public UserResponse setArticleCount(int articleCount) {
        this.articleCount = articleCount;
        return this;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public UserResponse setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public int getFollowCount() {
        return followCount;
    }

    public UserResponse setFollowCount(int followCount) {
        this.followCount = followCount;
        return this;
    }

    public UserResponse setAvgRateScore(double avgRateScore) {
        this.avgRateScore = avgRateScore;
        return this;
    }


    public double getAvgRateScore() {
        return avgRateScore;
    }

}
