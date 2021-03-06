package com.readapp.backend.dao;

import com.readapp.backend.models.Article;
import com.readapp.backend.models.Like;
import com.readapp.backend.models.RecentActivity;
import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeDao extends JpaRepository<Like, Long> {

    @Query(value = "SELECT l FROM Like l WHERE l.fromUser = ?1 AND l.toArticle = ?2")
    Like findByUserAndArticle(User user, Article article);

    @Query(value = "SELECT l FROM Like l WHERE l.fromUser = ?1 AND l.toRecentActivity = ?2")
    Like findByUserAndRecentActivity(User user, RecentActivity recentActivity);

    @Query(value = "SELECT l FROM Like l WHERE l.fromUser = ?1 AND l.toRecentActivity = ?2 AND l.dislike = false")
    Like findActiveByUserAndRecentActivity(User user, RecentActivity recentActivity);

    @Query(value = "SELECT l FROM Like l WHERE l.fromUser = ?1 AND  l.toArticle is not Null")
    Page<Like> findLikedArticlesByFromUser(User user, Pageable pageable);
}
