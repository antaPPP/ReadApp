package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.ArticleService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnchorController {
    @Autowired
    ArticleService articleService;

    @PostMapping(path = "/article/view")
    @RequiresAuthentication
    @AutoRefreshToken
    public Response addArticleViewCount(@RequestHeader("Authorization") String Authorization,
                                        @RequestParam("articleId") Long articleId){
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            articleService.addViewCount(uid, articleId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }
}
