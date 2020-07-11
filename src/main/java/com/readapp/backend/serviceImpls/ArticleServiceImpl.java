package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.*;
import com.readapp.backend.dto.ArticleResponse;
import com.readapp.backend.dto.CommentResponse;
import com.readapp.backend.dto.ReplyResponse;
import com.readapp.backend.models.*;
import com.readapp.backend.models.http.ArticleForm;
import com.readapp.backend.models.http.CommentForm;
import com.readapp.backend.models.http.ReplyForm;
import com.readapp.backend.services.ArticleService;
import com.readapp.backend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProfileDao profileDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    ReplyDao replyDao;

    @Autowired
    LikeDao likeDao;

    @Override
    public void addLike(Long uid, Long articleId) throws Exception {

        Like like = likeDao.findByUserAndArticle(new User().setId(uid), new Article().setId(articleId));
        Article article = articleDao.getOne(articleId);

        if (like == null && article != null) {

            article.setLikeCount(article.getLikeCount() + 1);

            article = articleDao.save(article);

            like = new Like();
            like.setFromUser(new User().setId(uid));
            like.setToArticle(article);

            likeDao.save(like);
        }

    }

    @Override
    public void deleteLike(Long uid, Long articleId) throws Exception {

        Like like = likeDao.findByUserAndArticle(new User().setId(uid), new Article().setId(articleId));

        Article article = articleDao.getOne(articleId);

        if (like != null && article != null) {
            article.setLikeCount(article.getLikeCount() - 1);
            articleDao.save(article);
            likeDao.delete(like);
        }

    }

    @Override
    public Comment addComment(CommentForm form) throws Exception {

        if (!articleDao.existsById(form.getToArticle())) return null;

        Comment comment = new Comment();
        comment.setFromUser(new User().setId(form.getFromUser()));
        comment.setContent(form.getContent());
        comment.setLikeCount(0);
        comment.setReplyCount(0);
        comment.setToArticle(new Article().setId(form.getToArticle()));

        comment = commentDao.save(comment);
        comment.getFromUser().setProfile(profileDao.findByUserId(new User().setId(form.getFromUser())));

        return comment;

    }

    @Override
    public void deleteComment(Long cid) throws Exception {
        Comment comment = commentDao.getOne(cid);
        if (comment == null) return;
        commentDao.delete(comment);
    }

    @Override
    public ArticleResponse addArticle(ArticleForm form) throws Exception {
        Long uid = form.getFromUser();
        User user = userDao.getOne(uid);

        if (user == null) throw new NoSuchElementException();

        Article article = new Article();
        article.setAuthor(form.getAuthor());
        article.setContent(form.getContent());
        article.setTitle(form.getTitle());
        article.setCoverUrl(form.getCoverUrl());
        article.setCommentCount(0);
        article.setLikeCount(0);
        article.setRateScore(0.);
        article.setExcerpt(form.getExcerpt());
        article.setFromUser(user);

        article = articleDao.save(article);

        return new ArticleResponse(article);
    }

    @Override
    public ArticleResponse editArticle(Long uid, Long articleId, ArticleForm form) throws Exception {

        Article article = articleDao.getOne(articleId);

        if (article == null || !article.getFromUser().getId().equals(uid)) throw new NoSuchElementException();

        article.setAuthor(StringUtils.isNotBlank(form.getAuthor()) ? form.getAuthor() : article.getAuthor());
        article.setContent(StringUtils.isNotBlank(form.getContent()) ? form.getContent() : article.getContent());
        article.setCoverUrl(StringUtils.isNotBlank(form.getCoverUrl()) ? form.getCoverUrl() : article.getCoverUrl());
        article.setExcerpt(StringUtils.isNotBlank(form.getExcerpt()) ? form.getExcerpt() : article.getExcerpt());
        article.setTitle(StringUtils.isNotBlank(form.getTitle()) ? form.getExcerpt() : article.getTitle());

        article = articleDao.save(article);

        return new ArticleResponse(article);
    }

    @Override
    public ArticleResponse getArticle(Long id) throws Exception {
        Article article = articleDao.getOne(id);

        if (article == null) throw new NoSuchElementException();
        return new ArticleResponse(article);
    }

    @Override
    public List<ArticleResponse> getLikedArticles(Long uid, int page, int capacity) throws Exception {
        return null;
    }

    @Override
    public List<ArticleResponse> getArticles(Long uid, int page, int capacity) throws Exception {

        Pageable pageable = PageRequest.of(
                page - 1,
                capacity
        );

        Page<Article> articles = articleDao.findByUser(new User().setId(uid), pageable);
        List<ArticleResponse> responses = new ArrayList<>();

        if (articles != null) {
            for (Article article : articles.toList()) {
                responses.add(new ArticleResponse(article));
            }
            return responses;
        }

        return responses;
    }

    @Override
    public List<CommentResponse> getArticleComments(Long articleId, int page, int capacity) throws Exception {
        Pageable pageable = PageRequest.of(
                page - 1,
                capacity
        );
        System.out.println(articleId);
        Page<Comment> comments = commentDao.findByArticle(new Article().setId(articleId), pageable);
        System.out.println("Count: " + comments.toList().size());
        List<CommentResponse> responses = new ArrayList<>();

        for (Comment comment : comments.toList()) {
            responses.add(new CommentResponse(comment));
        }

        return responses;
    }

    @Override
    public Reply addReply(ReplyForm form) throws Exception {
        if (!commentDao.existsById(form.getToComment())) return null;
//        if (StringUtils.isNotBlank(String.valueOf(form.getToReply())) && !replyDao.existsById(form.getToReply())) return null;
        Reply reply = new Reply();
        reply.setFromUser(new User().setId(form.getFromUser()));
        reply.setContent(form.getContent());
        reply.setToComment(commentDao.getOne(form.getToComment()));
        if (form.getToReply() != null) reply.setToReply(replyDao.getOne(form.getToReply()));
        reply = replyDao.save(reply);
        reply.getFromUser().setProfile(profileDao.findByUserId(new User().setId(form.getFromUser())));

        return reply;

    }

    @Override
    public void deleteReply(Long rid) throws Exception {

    }

    @Override
    public List<ReplyResponse> getCommentReplies(Long commentId, int page, int capacity) throws Exception {
        Pageable pageable = PageRequest.of(
                page,
                capacity
        );

        Page<Reply> replies = replyDao.findByComment(new Comment().setId(commentId), pageable);
        List<ReplyResponse> responses = new ArrayList<>();

        if (replies != null) {
            for (Reply reply : replies.toList()) {
                responses.add(new ReplyResponse(reply));
            }
            return responses;
        }

        return responses;
    }

    @Override
    public boolean checkLiked(Long uid, Long articleId) {
        Like like = likeDao.findByUserAndArticle(new User().setId(uid), new Article().setId(articleId));
        return like != null;
    }
}
