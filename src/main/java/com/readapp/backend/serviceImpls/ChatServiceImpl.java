package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.ChatDao;
import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.Chat;
import com.readapp.backend.models.User;
import com.readapp.backend.models.http.MessageForm;
import com.readapp.backend.services.ChatService;
import com.readapp.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ChatDao chatDao;
    @Autowired
    UserDao userDao;

    @Override
    public void sendMessage(MessageForm form) throws Exception {

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

}
