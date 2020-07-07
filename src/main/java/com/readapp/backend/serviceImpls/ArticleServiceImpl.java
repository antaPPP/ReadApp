package com.readapp.backend.serviceImpls;

import com.readapp.backend.dto.ArticleResponse;
import com.readapp.backend.dto.CommentResponse;
import com.readapp.backend.models.http.ArticleForm;
import com.readapp.backend.models.http.CommentForm;
import com.readapp.backend.services.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Override
    public void addLike(Long uid, Long articleId) throws Exception {

    }

    @Override
    public void deleteLike(Long uid, Long articleId) throws Exception {

    }

    @Override
    public void addComment(CommentForm form) throws Exception {

    }

    @Override
    public void deleteComment(Long cid) throws Exception {

    }

    @Override
    public ArticleResponse addArticle(ArticleForm form) throws Exception {
        return null;
    }

    @Override
    public ArticleResponse editArticle(Long uid, ArticleForm form) throws Exception {
        return null;
    }

    @Override
    public ArticleResponse getArticle(Long id) throws Exception {
        return null;
    }

    @Override
    public List<ArticleResponse> getLikedArticles(Long uid, int page, int capacity) throws Exception {
        return null;
    }

    @Override
    public List<ArticleResponse> getArticles(Long uid, int page, int capacity) throws Exception {
        return null;
    }

    @Override
    public List<CommentResponse> getArticleComments(Long articleId, int page, int capacity) throws Exception {
        return null;
    }
}
