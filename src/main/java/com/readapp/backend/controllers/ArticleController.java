package com.readapp.backend.controllers;

import com.readapp.backend.dto.CommentResponse;
import com.readapp.backend.dto.ReplyResponse;
import com.readapp.backend.models.Comment;
import com.readapp.backend.models.Reply;
import com.readapp.backend.models.http.*;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.ArticleService;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class  ArticleController {

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
            return Response.success(articleService.addArticle(form.setFromUser(uid)));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public Response getArticle(
           @RequestParam(value = "userId", required = false) Long uid,
           @RequestParam("id") Long id) {
        try {
            if (uid != null) {
                return Response.success(articleService.getArticle(id).setLiked(articleService.checkLiked(uid, id)));
            }
            return Response.success(articleService.getArticle(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/article/like", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response addArticleLike(@RequestHeader("Authorization") String Authorization,
                                   @RequestParam("toArticle") Long articleId) {
        Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
        try {
            articleService.addLike(uid, articleId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

    @RequestMapping(value = "/article/like", method = RequestMethod.DELETE)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response deleteArticleLike(@RequestHeader("Authorization") String Authorization,
                                   @RequestParam("toArticle") Long articleId) {
        Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
        try {
            articleService.deleteLike(uid, articleId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

    @RequestMapping(value = "/article/comment", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response addArticleComment(@RequestHeader("Authorization") String Authorization,
                                      @RequestBody CommentForm form) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            form.setFromUser(uid);
            Comment comment = articleService.addComment(form);
            return Response.success(new CommentResponse(comment));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/article/comments", method = RequestMethod.GET)
    public Response getArticleComments(@RequestParam("id") Long id,
                                       @RequestParam("page") int page,
                                       @RequestParam(value = "withRate", defaultValue = "false") boolean withRate,
                                       @RequestParam("capacity") int capacity) {
        try {
            if (withRate) return Response.success(articleService.getArticleCommentsWithRate(id, page, capacity));
            return Response.success(articleService.getArticleComments(id, page, capacity));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }



    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getUserArticles(@RequestHeader("Authorization") String Authorization,
                                    @RequestParam(value = "id", required = false) Long id,
                                    @RequestParam("page") int page,
                                    @RequestParam("capacity") int capacity) {
        try {
            if (id == null) {
                Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
                return Response.success(articleService.getDetailedArticles(uid, page, capacity));
            } else {
                return Response.success(articleService.getArticles(id, page, capacity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/article/rate", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response addArticleRate(@RequestHeader("Authorization") String Authorization,
                                   @RequestParam("toArticle") Long toArticle,
                                   @RequestParam("score") Double score) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            return Response.success(articleService.addRate(uid, toArticle, score));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/article/rate", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getUserArticleRate(@RequestHeader("Authorization") String Authorization,
                                   @RequestParam("articleId") Long articleId){
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            return Response.success(articleService.getArticleRate(uid, articleId));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

}
