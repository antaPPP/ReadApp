package com.readapp.backend.services;

import com.readapp.backend.dto.ArticleResponse;
import com.readapp.backend.dto.CommentResponse;
import com.readapp.backend.dto.ReplyResponse;
import com.readapp.backend.models.Article;
import com.readapp.backend.models.Comment;
import com.readapp.backend.models.Reply;
import com.readapp.backend.models.http.ArticleForm;
import com.readapp.backend.models.http.CommentForm;
import com.readapp.backend.models.http.ReplyForm;

import java.util.List;

public interface ArticleService {
    void addLike(Long uid, Long articleId) throws Exception;
    void deleteLike(Long uid, Long articleId) throws Exception;
    Comment addComment(CommentForm form) throws Exception;
    void deleteComment(Long cid) throws Exception;
    Reply addReply(ReplyForm form) throws Exception;
    void deleteReply(Long rid) throws Exception;
    ArticleResponse addArticle(ArticleForm form) throws Exception;
    ArticleResponse editArticle(Long uid, Long articleId, ArticleForm form) throws Exception;
    ArticleResponse getArticle(Long id) throws Exception;
    List<ArticleResponse> getLikedArticles(Long uid, int page, int capacity) throws Exception;
    List<ArticleResponse> getArticles(Long uid, int page, int capacity) throws Exception;
    List<ArticleResponse> getDetailedArticles(Long uid, int page, int capacity) throws Exception;
    List<CommentResponse> getArticleComments(Long articleId, int page, int capacity) throws Exception;
    List<ReplyResponse> getCommentReplies(Long commentId, int page, int capacity) throws Exception;

    boolean checkLiked(Long uid, Long articleId);
}
