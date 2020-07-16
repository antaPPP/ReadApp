package com.readapp.backend.dao;

import com.readapp.backend.models.Article;
import com.readapp.backend.models.Like;
import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeDao extends JpaRepository<Like, Long> {

    @Query(value = "SELECT l FROM Like l WHERE l.fromUser = ?1 AND l.toArticle = ?2")
    Like findByUserAndArticle(User user, Article article);

    @Query(value = "SELECT l FROM Like l WHERE l.fromUser = ?1 AND  l.toArticle is not Null")
    Page<Like> findLikedArticlesByFromUser(User user, Pageable pageable);

    @Query(value = "SELECT l FROM Like l WHERE l.user = ?1 AND l.type = ?2 AND l.article = ?3")
    Like findByArticleUserAndType(User user, String type, Article article);
}
