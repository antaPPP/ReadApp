package com.readapp.backend.controllers;

import com.readapp.backend.dto.ReplyResponse;
import com.readapp.backend.models.Reply;
import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.ReplyForm;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.ArticleService;
import com.readapp.backend.services.RecentActivityService;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("all")
@RestController
public class ReplyController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    RecentActivityService recentActivityService;

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
           Reply reply = articleService.addReply(form);
           return Response.success(new ReplyResponse(reply));
       } catch (Exception e) {
           e.printStackTrace();
           return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
       }
    }

    @RequestMapping(value = "/recent_activity/reply", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response addRecentActivityReply(@RequestHeader("Authorization") String Authorization,
                                           @RequestBody ReplyForm form) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            form.setFromUser(uid);
            Reply reply = recentActivityService.addReply(form);
            return Response.success(new ReplyResponse(reply));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

}
