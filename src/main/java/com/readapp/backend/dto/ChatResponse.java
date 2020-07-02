package com.readapp.backend.dto;

import com.readapp.backend.models.Chat;
import com.readapp.backend.models.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatResponse {

    private Long id;

    private String type;
    private String name;

    private UserResponse fromUser;
    private UserResponse toUser;

    private List<UserResponse> members;
    private List<UserResponse> allMembers;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ChatResponse() {
    }

    public ChatResponse(Chat chat){
        this.id = chat.getId();
        this.name = chat.getName();
        this.members = new ArrayList<>();
        this.type = chat.getType();
        this.allMembers = new ArrayList<>();
        if (chat.getMembers() != null) {
            for (User user : chat.getMembers()) {
                members.add(new UserResponse(
                        user
                ));
            }
        }

        if (chat.getAllMembers() != null) {
            for (User user : chat.getAllMembers()) {
                allMembers.add(new UserResponse(
                        user
                ));
            }
        }
    }

    public String getType() {
        return type;
    }

    public ChatResponse setType(String type) {
        this.type = type;
        return this;
    }

    public UserResponse getFromUser() {
        return fromUser;
    }

    public ChatResponse setFromUser(UserResponse fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public UserResponse getToUser() {
        return toUser;
    }

    public ChatResponse setToUser(UserResponse toUser) {
        this.toUser = toUser;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public ChatResponse setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public ChatResponse setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ChatResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChatResponse setName(String name) {
        this.name = name;
        return this;
    }

    public List<UserResponse> getMembers() {
        return members;
    }

    public ChatResponse setMembers(List<UserResponse> members) {
        this.members = members;
        return this;
    }

    public List<UserResponse> getAllMembers() {
        return allMembers;
    }

    public ChatResponse setAllMembers(List<UserResponse> allMembers) {
        this.allMembers = allMembers;
        return this;
    }
}
