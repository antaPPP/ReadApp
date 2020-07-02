package com.readapp.backend.models.utils;

import com.readapp.backend.dto.MessageResponse;
import com.readapp.backend.dto.UserResponse;
import com.readapp.backend.models.Message;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChatPreviewInfo implements Serializable {
    private Long chatId;
    private int unreadCount;
    private UserResponse user;
    private MessageResponse lastMessage;
    private Timestamp lastMsgAt;

    public Long getChatId() {
        return chatId;
    }

    public UserResponse getUser() {
        return user;
    }

    public ChatPreviewInfo setUser(UserResponse user) {
        this.user = user;
        return this;
    }

    public ChatPreviewInfo setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public ChatPreviewInfo setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
        return this;
    }

    public MessageResponse getLastMessage() {
        return lastMessage;
    }

    public ChatPreviewInfo setLastMessage(MessageResponse lastMessage) {
        this.lastMessage = lastMessage;
        return this;
    }

    public Timestamp getLastMsgAt() {
        return lastMsgAt;
    }

    public ChatPreviewInfo setLastMsgAt(Timestamp lastMsgAt) {
        this.lastMsgAt = lastMsgAt;
        return this;
    }

    @Override
    public String toString() {
        return "ChatPreviewInfo{" +
                "chatId=" + chatId +
                ", unreadCount=" + unreadCount +
                ", lastMessage=" + lastMessage +
                ", lastMsgAt=" + lastMsgAt +
                '}';
    }
}
