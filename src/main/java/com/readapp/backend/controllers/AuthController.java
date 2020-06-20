package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AuthController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/api/v1/verification", method = RequestMethod.GET)
    public Response requestVerificationCode(@RequestParam("countryCode") String countryCode, @RequestParam("mobile") String mobile) {
        try {
            authService.requestVerificationCode(mobile);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

}
