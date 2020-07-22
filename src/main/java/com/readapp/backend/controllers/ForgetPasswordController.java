package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.utils.SMSForm;
import com.readapp.backend.services.AuthService;
import com.readapp.backend.services.ForgetPasswordService;
import com.readapp.backend.services.SmsService;
import com.readapp.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.readapp.backend.models.http.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class ForgetPasswordController {
    @Autowired
    ForgetPasswordService forgetPasswordService;
    @Autowired
    SmsService smsService;
    @Autowired
    AuthService authService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping (value = "/forgetPassword/verification", method = RequestMethod.GET)
    public Response getVerificationRequest(
            @RequestParam("countryCode") String countryCode,
            @RequestParam("mobile") String mobile,
            @RequestParam("verificationCode") String verificationCode) {
        try {
            authService.requestVerificationCode(countryCode, mobile);
            authService.verifyCode(countryCode, mobile, verificationCode);
            return Response.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }


    @RequestMapping (value = "/forgetPassword/update", method = RequestMethod.POST)
    public Response updatePassword(
            @RequestParam("countryCode") String countryCode,
            @RequestParam("mobile") String mobile,
            @RequestParam("newPassword") String newPassword) {
        try {
            forgetPasswordService.setNewPassword(countryCode, mobile, newPassword);
            return Response.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
