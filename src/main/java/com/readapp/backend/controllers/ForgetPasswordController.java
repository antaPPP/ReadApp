package com.readapp.backend.controllers;

import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.http.AuthForm;
import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.services.AuthService;
import com.readapp.backend.services.ForgetPasswordService;
import com.readapp.backend.services.SmsService;
import com.readapp.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.readapp.backend.models.http.Response;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForgetPasswordController {
    @Autowired
    ForgetPasswordService forgetPasswordService;
    @Autowired
    SmsService smsService;
    @Autowired
    AuthService authService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserDao userDao;

    @RequestMapping (value = "/forgetPassword/update", method = RequestMethod.POST)
    public Response updatePassword(
            @RequestParam("countryCode") String countryCode,
            @RequestParam("mobile") String mobile,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("verificationCode") String verificationCode) {
        AuthForm result = null;
        try {
            //sent the verification code and verify
            authService.verifyCode(countryCode, mobile, verificationCode);
            //update the database and the token
            forgetPasswordService.setNewPassword(countryCode, mobile, newPassword);
            result = authService.loginByPassword(countryCode, mobile, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(result);
    }
}
