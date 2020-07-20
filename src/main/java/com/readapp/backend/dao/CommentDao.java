package com.readapp.backend.dao;

import com.readapp.backend.models.Article;
import com.readapp.backend.models.Comment;
import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentDao extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c WHERE c.toArticle = ?1")
    Page<Comment> findByArticle(Article article, Pageable pageable);

    @Query(value = "SELECT c FROM Comment c WHERE c.toArticle = ?1 AND c.rate IS NOT NULL")
    Page<Comment> findByArticleWithRate(Article article, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Comment c SET c.rate = ?3 WHERE c.fromUser = ?1 AND c.toArticle = ?2")
    void addRate(User fromUser, Article article, Double rate);
}
