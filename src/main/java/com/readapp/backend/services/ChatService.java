package com.readapp.backend.services;


import com.readapp.backend.models.Chat;
import com.readapp.backend.models.http.MessageForm;
import com.readapp.backend.models.utils.ChatPreviewInfo;

import java.util.Map;

public interface ChatService {
    void sendMessage(MessageForm form) throws Exception;
    Chat createDirectChat(Long fromUser, Long toUser) throws Exception;
    Map<String, ChatPreviewInfo> fetchAllEvents(Long id) throws Exception;
}
