package com.readapp.backend.controllers;

import com.readapp.backend.models.http.ArticleForm;
import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.ArticleService;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response createArticle(@RequestHeader("Authorization") String Authorization,
                                  @RequestBody ArticleForm form){
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            if (!uid.equals(form.getFromUser())) throw new Exception();
            return Response.success(articleService.addArticle(form));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public Response getArticle(@RequestParam("id") Long id) {
        try {
            return Response.success(articleService.getArticle(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }


}
