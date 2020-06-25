package com.readapp.backend.serviceImpls;

import com.alibaba.fastjson.JSONObject;
import com.readapp.backend.controllers.SocketSession;
import com.readapp.backend.dao.ChatDao;
import com.readapp.backend.dao.MessageDao;
import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.Chat;
import com.readapp.backend.models.Message;
import com.readapp.backend.models.User;
import com.readapp.backend.models.events.Event;
import com.readapp.backend.models.http.MessageForm;
import com.readapp.backend.models.utils.ChatPreviewInfo;
import com.readapp.backend.services.ChatService;
import com.readapp.backend.utils.ChatUtils;
import com.readapp.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ChatUtils chatUtils;
    @Autowired
    ChatDao chatDao;
    @Autowired
    UserDao userDao;
    @Autowired
    MessageDao messageDao;

    @Override
    @Transactional
    public void sendMessage(MessageForm form) throws Exception {

        /*
         * Save message first before emit the event
         */

        Long chatId = form.getToChat();
        Chat chat = chatDao.findById(chatId).get();

        Message message = new Message();
        message.setContent(form.getContent());
        message.setType(form.getType());
        message.setChat(chat);
        message.setToUser(new User().setId(form.getToUser()));
        message.setFromUser(new User().setId(form.getFromUser()));

        message = messageDao.save(message);

//        message.getFromUser().getProfile().setUser(null);
        message.getFromUser().setProfile(null);

        if (SocketSession.webSocketMap.containsKey(String.valueOf(form.getToUser()))) {
            SocketSession.webSocketMap.get(String.valueOf(form.getToUser())).sendMessage(
                    JSONObject.toJSONString(new Event().setData(message).setType("message"))
            );
            System.out.println("Sending");
        } else {
            System.out.println("push to chat map");
            chatUtils.push(message);
        }
    }

    @Override
    public Chat createDirectChat(Long fromUser, Long toUser) throws Exception {

        Optional<User> fromOptionalUser = userDao.findById(fromUser);
        Optional<User> toOptionalUser = userDao.findById(toUser);

        if (!fromOptionalUser.isPresent() || !toOptionalUser.isPresent()) {
            throw new EntityNotFoundException();
        }

        User from = fromOptionalUser.get();
        User to = toOptionalUser.get();

        Chat chat = new Chat();

        chat.setAllMembers(new ArrayList<>());
        chat.setMembers(new ArrayList<>());

        chat.setType("Direct");
        chat.setMessages(new ArrayList<>());

        chat.getAllMembers().add(from);
        chat.getAllMembers().add(to);

        chat.getMembers().add(from);
        chat.getMembers().add(to);

        chat = chatDao.save(chat);

        return chat;
    }

    @Override
    public Map<String, ChatPreviewInfo> fetchAllEvents(Long id) throws Exception {

        Map<String, ChatPreviewInfo> chatMap = chatUtils.popAll(id);

        if (chatMap == null) return new HashMap<>();

        return chatMap;
    }
}
