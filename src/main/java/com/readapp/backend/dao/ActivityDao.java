package com.readapp.backend.dao;

import com.readapp.backend.models.Activity;
import com.readapp.backend.models.User;
import com.readapp.backend.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
@Repository
public interface ActivityDao extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT a FROM Activity a WHERE a.user = ?1 AND a.type = ?2")
    Page<Activity> findByType(User user, String type, Pageable pageable);

    @Query(value = "SELECT count(a) FROM Activity a WHERE a.user = ?1 AND a.type = ?2 AND a.createdAt > ?3")
    Integer countByCreatedAt(User user, String type, Date cursorAt);
}
