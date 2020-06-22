package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.models.utils.SignUpForm;
import com.readapp.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    AuthService authService;

    @RequestMapping(value = "/auth/verification", method = RequestMethod.GET)
    public Response requestVerificationCode(@RequestParam("countryCode") String countryCode, @RequestParam("mobile") String mobile) {
        try {
            authService.requestVerificationCode(countryCode, mobile);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

    @RequestMapping(value = "/auth/user", method = RequestMethod.POST)
    public Response signup(@RequestParam("mobile") String mobile,
                           @RequestParam("countryCode") String countryCode,
                           @RequestParam("verificationCode") String verificationCode,
                           @RequestParam("password") String password) {
        SignUpForm form = new SignUpForm().setCountryCode(countryCode)
                            .setMobile(mobile)
                            .setPassword(password)
                            .setVerificationCode(verificationCode);
        String code = "";
        try {
            authService.signUp(form);
            code = authService.loginByPassword(countryCode, mobile, password);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(code);
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public Response loginByPassword(@RequestParam("countryCode") String countryCode,
                          @RequestParam("mobile") String mobile,
                          @RequestParam("password") String password){
        String token = "";
        try {
            token = authService.loginByPassword(countryCode, mobile, password);
        } catch (Exception e){
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(token);
    }

    @RequestMapping(value = "/error/401", method = RequestMethod.GET)
    public String unauthorized(){
        return "401";
    }

}