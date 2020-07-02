package com.readapp.backend.dto;

import com.readapp.backend.models.Profile;

public class ProfileResponse {
    private String nickname;
    private String whatsup;
    private String avatarUrl;
    private String coverUrl;

    public ProfileResponse(Profile profile) {
        this.nickname = profile.getNickname();
        this.whatsup = profile.getWhatsup();
        this.avatarUrl = profile.getAvatarUrl();
        this.coverUrl = profile.getCoverUrl();
    }

    public ProfileResponse(){}

    public String getNickname() {
        return nickname;
    }

    public ProfileResponse setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getWhatsup() {
        return whatsup;
    }

    public ProfileResponse setWhatsup(String whatsup) {
        this.whatsup = whatsup;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public ProfileResponse setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public ProfileResponse setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

}
