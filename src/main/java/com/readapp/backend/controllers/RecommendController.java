package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.RecommendService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @RequestMapping(value = "/recommend/article", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getRecommendArticle(@RequestHeader("Authorization") String Authorization) {
        try {
            return Response.success(recommendService.getRecommendArticle(Long.parseLong(JWTUtil.getUserId(Authorization))));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR ,e.getLocalizedMessage());
        }
    }

}
