package com.readapp.backend.dao;

import com.readapp.backend.models.Article;
import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {

    @Query(value = "SELECT a FROM Article a WHERE a.fromUser = ?1")
    Page<Article> findByUser(User user, Pageable pageable);

}
