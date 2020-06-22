package com.readapp.backend;

import com.readapp.backend.models.User;
import com.readapp.backend.models.utils.SMSForm;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.RNG;
import com.readapp.backend.utils.RedisUtil;
import com.readapp.backend.utils.SMSUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;

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
        redisUtil.set("k", "v", 1000L);
        System.out.println((String)redisUtil.get("k"));
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

}
