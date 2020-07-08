package com.readapp.backend;

import com.readapp.backend.dao.ChatDao;
import com.readapp.backend.dao.CommentDao;
import com.readapp.backend.dao.MessageDao;
import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.Article;
import com.readapp.backend.models.Chat;
import com.readapp.backend.models.Message;
import com.readapp.backend.models.User;
import com.readapp.backend.models.http.MessageForm;
import com.readapp.backend.models.utils.ChatPreviewInfo;
import com.readapp.backend.models.utils.SMSForm;
import com.readapp.backend.services.ChatService;
import com.readapp.backend.services.FileService;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.ChatUtils;
import com.readapp.backend.utils.RNG;
import com.readapp.backend.utils.RedisUtil;
import com.readapp.backend.utils.SMSUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.*;

@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;
    @Autowired
    ChatDao chatDao;
    @Autowired
    ChatUtils chatUtils;
    @Autowired
    ChatService chatService;
    @Autowired
    MessageDao messageDao;
    @Autowired
    FileService fileService;
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
    }

    @Test
    void testRNG() {
        System.out.println(RNG.generateVerificationCode(6));
    }

    @Test
    void testSMS(){
        SMSForm form = new SMSForm();
        form.setAction("SendSms");
        form.setCode(RNG.generateVerificationCode(6));
        form.setMobile("15838371220");
        SMSUtils.sendVerificationSMS(form);
    }

    @Test
    void testRedis() throws Exception {
        redisUtil.del("chat." + 1L);
    }

    @Test
    void testUser() throws Exception {
        User user = userService.getUser(1L);
        System.out.println(user.getTags().size());
    }

    @Test
    void testTags() {
        User user = userService.getUser(1L);
    }

    @Test
    void testChat(){
        List<User> list = new ArrayList<>();
        list.add(new User().setId(1L));
        chatDao.save(new Chat().setMembers(list).setAllMembers(new ArrayList<>()).setMessages(new ArrayList<>()).setType("Direct"));
    }

    @Test
    void testMsg() throws Exception {

        chatService.sendMessage(new MessageForm()
                .setContent("Hello World! Read app!")
                .setFromUser(1L)
                .setToUser(1L)
                .setToChat(1L)
                .setType("text"));

    }

    @Test
    void testChatMap() throws Exception {
        Map<String, ChatPreviewInfo> rst = chatUtils.get(1L);
        System.out.println(rst.get("1").getLastMessage().getContent());
        System.out.println(rst.get("1").getUnreadCount());
    }

    @Test
    void testQiniu() throws Exception {
        System.out.println(fileService.getToken());
    }

    @Test
    void testPagination() throws Exception {
        userService.searchByKeyword("ick", 1, 10);
    }

    @Test
    void testFollow() throws Exception {
        userService.follow(1L, 2L);
        User user = userService.getUser(1L);
        User host = userService.getUser(2L);
        System.out.println(user.getFollowCount());
        System.out.println(host.getFollowerCount());
    }

    @Test
    void testFollow2() throws Exception {
        List<Long> list = userService.getFollowingIDs(1L);
        System.out.println(list.get(0));
    }

    @Test
    void testFindChat() throws Exception {
        chatService.createDirectChat(1L, 2L);
        System.out.println(chatService.getDirectChatByMembers(1L, 2L));
    }

    @Test
    void testFindChatMembers() throws Exception {
        List<User> members = chatDao.findMembersId(1L);
        System.out.println(members.size());
    }

    @Test
    void testRandLong() {
        for (int i = 0; i < 30; i++) {
            System.out.println(RNG.nextLong(new Random(), 30L));
        }
    }

    @Test
    void testSearch() {
        System.out.println(userDao.searchByKeyword("dy", PageRequest.of(0, 10)).toList().size());
    }

}
