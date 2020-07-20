package com.readapp.backend.dao;

import com.readapp.backend.models.Article;
import com.readapp.backend.models.PageView;
import com.readapp.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PageViewDao extends JpaRepository<PageView, Long> {

    @Query(value = "SELECT p FROM PageView p WHERE p.fromUser = ?1 AND p.toArticle = ?2")
    PageView findByFromUserAndToArticle(User user, Article article);
}
