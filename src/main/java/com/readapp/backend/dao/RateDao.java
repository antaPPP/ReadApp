package com.readapp.backend.dao;

import com.readapp.backend.models.Article;
import com.readapp.backend.models.Rate;
import com.readapp.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateDao extends JpaRepository<Rate, Long> {

    @Query(value = "SELECT r FROM Rate r WHERE r.fromUser = ?1 AND r.toArticle = ?2")
    Rate findByFromUserAndToArticle(User fromUser, Article toArticle);

    @Query(value = "SELECT COUNT(r) FROM Rate r WHERE r.toArticle = ?1")
    int countRates(Article article);
}
