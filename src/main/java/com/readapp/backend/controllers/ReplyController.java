package com.readapp.backend.controllers;

import com.readapp.backend.models.Reply;
import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.ReplyForm;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.ArticleService;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/article/comment/reply", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response addReply(
            @RequestHeader("Authorization") String Authorization,
            @RequestBody ReplyForm form
            ) {
       try {
           Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
           form.setFromUser(uid);
           articleService.addReply(form);
       } catch (Exception e) {
           e.printStackTrace();
           return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
       }
       return Response.success(null);
    }



}
