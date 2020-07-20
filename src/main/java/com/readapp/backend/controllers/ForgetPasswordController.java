package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.utils.SMSForm;
import com.readapp.backend.services.ForgetPasswordService;
import com.readapp.backend.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.readapp.backend.models.http.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class ForgetPasswordController {
    @Autowired
    ForgetPasswordService forgetPasswordService;
    SmsService smsService;

    public Response getForgetPasswordRequest(
            @RequestParam("username") Long uid,
            @RequestParam("newPassword") String newPassword,
            @RequestBody SMSForm form) {
        try {
            smsService.sendVerificationSMS(form);
            forgetPasswordService.setNewPassword(uid, newPassword);
            return Response.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
