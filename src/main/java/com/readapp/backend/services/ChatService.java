package com.readapp.backend.services;


import com.readapp.backend.dto.ChatResponse;
import com.readapp.backend.dto.MessageResponse;
import com.readapp.backend.models.Chat;
import com.readapp.backend.models.http.MessageForm;
import com.readapp.backend.models.utils.ChatPreviewInfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ChatService {
    void sendMessage(MessageForm form) throws Exception;

    Chat getDirectChatByMembers(Long fromUser, Long toUser) throws Exception;

    List<MessageResponse> findMessagesByChat(Long id, Long uid, int page, int size, Timestamp cursor) throws Exception;

    Chat createDirectChat(Long fromUser, Long toUser) throws Exception;

    List<ChatResponse> getChats(Long id) throws Exception;

    Chat getChat(Long id);

    Map<String, ChatPreviewInfo> fetchAllEvents(Long id) throws Exception;
}
