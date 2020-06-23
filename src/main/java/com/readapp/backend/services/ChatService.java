package com.readapp.backend.services;


import com.readapp.backend.models.Chat;
import com.readapp.backend.models.http.MessageForm;

public interface ChatService {
    void sendMessage(MessageForm form) throws Exception;
    Chat createDirectChat(Long fromUser, Long toUser) throws Exception;
}
