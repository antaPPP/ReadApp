package com.readapp.backend.dao;

import com.readapp.backend.models.Article;
import com.readapp.backend.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentDao extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c WHERE c.toArticle = ?1")
    Page<Comment> findByArticle(Article article, Pageable pageable);
}
