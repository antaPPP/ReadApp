package com.readapp.backend.serviceImpls;

import com.alibaba.fastjson.JSONObject;
import com.readapp.backend.controllers.SocketSession;
import com.readapp.backend.dao.ChatDao;
import com.readapp.backend.dao.MessageDao;
import com.readapp.backend.dao.UserDao;
import com.readapp.backend.dto.MessageResponse;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    /**
     * Client chat cycle: fetchAllEvents() -> Merge chat map -> getChatById -> Socket.onMessage -> Update chat
     */

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
        message.getChat().setAllMembers(null).setMembers(null);

        if (SocketSession.webSocketMap.containsKey(String.valueOf(form.getToUser()))) {
            SocketSession.webSocketMap.get(String.valueOf(form.getToUser())).sendMessage(
                    JSONObject.toJSONString(new Event().setData(new MessageResponse(message)).setType("message"))
            );
            System.out.println("Sending");
        } else {
            System.out.println("push to chat map");
            chatUtils.push(message);
        }
    }


    @Override
    public Chat getDirectChatByMembers(Long fromUser, Long toUser) throws Exception {
        User from = userDao.getOne(fromUser);
        User to = userDao.getOne(toUser);
        if (from == null || to == null) throw new NoSuchElementException();
        for (Chat chat : from.getChats()) {
            if (chat.getMembers().contains(to)) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public List<MessageResponse> findMessagesByChat(Long id, Long uid, int page, int size) throws Exception {

        if (this.checkMembers(id, uid)) throw new NoSuchElementException();

        Pageable pageable = PageRequest.of(page - 1,size);

        List<MessageResponse> responses = new ArrayList<>();

        for (Message message : messageDao.findMessagesByChat(new Chat().setId(id), pageable)) {
            responses.add(new MessageResponse(message));
        }

        return responses;
    }

    @Override
    public Chat createDirectChat(Long fromUser, Long toUser) throws Exception {

        Chat chat = this.getDirectChatByMembers(fromUser, toUser);
        if (chat != null) return chat;

        Optional<User> fromOptionalUser = userDao.findById(fromUser);
        Optional<User> toOptionalUser = userDao.findById(toUser);

        if (!fromOptionalUser.isPresent() || !toOptionalUser.isPresent()) {
            throw new EntityNotFoundException();
        }

        User from = fromOptionalUser.get();
        User to = toOptionalUser.get();

        chat = new Chat();

        chat.setAllMembers(new ArrayList<>());
        chat.setMembers(new ArrayList<>());

        chat.setType("Direct");
        chat.setMessages(new ArrayList<>());

        if (!chat.getAllMembers().contains(from)) chat.getAllMembers().add(from);
        if (!chat.getAllMembers().contains(to)) chat.getAllMembers().add(to);

        if (!chat.getMembers().contains(from)) chat.getMembers().add(from);
        if (!chat.getMembers().contains(to)) chat.getMembers().add(to);

        chat = chatDao.save(chat);

        return chat;
    }

    @Override
    public Map<String, ChatPreviewInfo> fetchAllEvents(Long id) throws Exception {

        Map<String, ChatPreviewInfo> chatMap = chatUtils.popAll(id);

        if (chatMap == null) return new HashMap<>();

        return chatMap;
    }

    private boolean checkMembers(Long cid, Long uid) {
        List<User> members = chatDao.findMembersId(cid);
        if (members == null) return false;
        return members.contains(new User().setId(uid));
    }
}
