package com.readapp.backend.utils;

import com.readapp.backend.dto.MessageResponse;
import com.readapp.backend.models.Message;
import com.readapp.backend.models.utils.ChatPreviewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Better Hash/Serialize operations for ChatMap in redis
 */
@Component
public class ChatUtils {

    @Autowired
    RedisUtil redisUtil;

    /**
     * Push a new message to the User-Chats Redis map
     * @param message
     * @throws Exception
     */
    public void push(Message message) throws Exception {
        if (!redisUtil.hasKey("chat." + message.getToUser().getId())) {
            /*
            Create chat{${userId} -- ChatPreviewInfo} map if no userId is found in redis key set
             */
            System.out.println("Creating new map entry: " + "chat." + message.getToUser().getId());
            Map<String, ChatPreviewInfo> map = new HashMap<>();

            map.put(String.valueOf(message.getChat().getId()), new ChatPreviewInfo()
                    .setChatId(message.getChat().getId())
                    .setLastMessage(new MessageResponse(message))
                    .setLastMsgAt(message.getCreatedAt())
                    .setUnreadCount(1)
            );
            redisUtil.set("chat." + message.getToUser().getId(), map);
        } else {
            /*
            Get the map if previous ${userId} is found
             */
            System.out.println("Found map entry: " + "chat." + message.getToUser().getId());
            Map<String, ChatPreviewInfo> chatMap = (Map<String, ChatPreviewInfo>) redisUtil.get("chat." + message.getToUser().getId());
            if (chatMap == null) chatMap = new HashMap<>();
            Long chatId = message.getChat().getId();
            if (!chatMap.containsKey(String.valueOf(chatId))) {
                /*
                Condition on no event found
                 */
                ChatPreviewInfo chat = new ChatPreviewInfo();
                chat.setChatId(chatId);
                chat.setLastMessage(new MessageResponse(message));
                chat.setUnreadCount(1);
                chat.setLastMsgAt(message.getCreatedAt());
                chatMap.put(String.valueOf(chatId), chat);
            } else {
                ChatPreviewInfo chatPreviewInfo = chatMap.get(String.valueOf(chatId));
                chatPreviewInfo.setUnreadCount(chatPreviewInfo.getUnreadCount() + 1);
                chatPreviewInfo.setLastMessage(new MessageResponse(message));
                chatPreviewInfo.setChatId(chatId);
                chatPreviewInfo.setLastMsgAt(message.getCreatedAt());
                chatMap.put(String.valueOf(chatId), chatPreviewInfo);
            }

            /*
            Push to redis cache
             */

            redisUtil.set("chat." + message.getToUser().getId(), chatMap);
        }
    }

    public Map<String, ChatPreviewInfo> popAll(Long uid) throws Exception {

        if (!redisUtil.hasKey("chat." + uid)) return null;

        Map<String, ChatPreviewInfo> chatMap = (Map<String, ChatPreviewInfo>)redisUtil.get("chat." + uid);

        redisUtil.del("chat." + uid);

        return chatMap;
    }

    public Map<String, ChatPreviewInfo> get(Long uid) throws Exception {
        if (!redisUtil.hasKey("chat." + uid)) return null;
        Map<String, ChatPreviewInfo> chatMap = (Map<String, ChatPreviewInfo>)redisUtil.get("chat." + uid);
        return chatMap;
    }
}
