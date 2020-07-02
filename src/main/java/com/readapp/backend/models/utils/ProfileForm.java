package com.readapp.backend.models.utils;

import com.readapp.backend.models.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileForm {
    private String nickname = "";
    private String whatsup = "";
    private String avatarUrl = "";
    private String coverUrl = "";
    private List<String> tags = new ArrayList<>();

    public ProfileForm(){}

    public ProfileForm(Profile profile) {
        if (profile == null) return;
        if(profile.getNickname() != null) this.nickname = profile.getNickname();
        if(profile.getWhatsup() != null) this.whatsup = profile.getWhatsup();
        if(profile.getAvatarUrl() != null) this.avatarUrl = profile.getAvatarUrl();
        if(profile.getCoverUrl() != null) this.coverUrl = profile.getCoverUrl();
    }

    public String getNickname() {
        return nickname;
    }

    public ProfileForm setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getWhatsup() {
        return whatsup;
    }

    public ProfileForm setWhatsup(String whatsup) {
        this.whatsup = whatsup;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public ProfileForm setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public ProfileForm setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public ProfileForm setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
