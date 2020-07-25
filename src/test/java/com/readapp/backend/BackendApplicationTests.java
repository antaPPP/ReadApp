package com.readapp.backend;

import com.alibaba.fastjson.JSONArray;
import com.readapp.backend.dao.*;
import com.readapp.backend.dto.ActivityResponse;
import com.readapp.backend.models.*;
import com.readapp.backend.models.http.MessageForm;
import com.readapp.backend.models.utils.ChatPreviewInfo;
import com.readapp.backend.models.utils.SMSForm;
import com.readapp.backend.services.*;
import com.readapp.backend.utils.ChatUtils;
import com.readapp.backend.utils.RNG;
import com.readapp.backend.utils.RedisUtil;
import com.readapp.backend.utils.SMSUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    @Autowired
    ArticleService articleService;
    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityDao activityDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    ForgetPasswordService forgetPasswordService;

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
    void testFindChats() throws Exception {
        List<Chat> chats = chatDao.findByMembers_Id(1L);
        System.out.println(chats.get(0).getId());
    }

    @Test
    void testSearch() {
        System.out.println(userDao.searchByKeyword("dy", PageRequest.of(0, 10)).toList().size());
    }

    @Test
    void testLikeArticle() throws Exception{
        articleService.addLike(3L, 2L);
    }

    @Test
    void testActivities() throws Exception {
        List<ActivityResponse> responses = activityService.getLikeActivities(1L,  1, 10);
        System.out.println(responses.size());
    }

    @Test
    void testActivityCursor() throws Exception {
        System.out.println(activityDao.countByCreatedAt(new User().setId(1L), "comment", new Date(183938999999994L)));
    }

    @Test
    void testMilktea() throws Exception {
        SMSUtils.sendMilteaMessage();
    }

    @Test
    void testUpdateCommentRates() throws Exception {
        commentDao.addRate(new User().setId(1L), new Article().setId(2L), 9.0);
    }

    @Test
    void testJSON() throws Exception {
        String ary = "[\"1\", \"2\"]";
        JSONArray array = JSONArray.parseArray(ary);
        System.out.println(array.get(0).toString());
    }

    @Test
    void testFP() throws Exception {
        User user = new User();
        user.setPassword("33402488");
        user.setCountryCode("3");
        user.setMobile("34");
        System.out.println(user.getPassword());
        System.out.println(user.getCountryCode());
        System.out.println(user.getMobile());
        userDao.save(user);

        user = forgetPasswordService.setNewPassword("3", "34", "12345");
        System.out.println(user.getPassword());
    }

}
