package com.readapp.backend.dao;

import com.readapp.backend.models.RecentActivity;
import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RecentActivityDao extends JpaRepository<RecentActivity, Long> {

    @Query(value = "SELECT r FROM RecentActivity r WHERE r.user = ?1 AND r.createdAt < ?2 ORDER BY r.createdAt DESC")
    Page<RecentActivity> findByUserWithCursor(User user, Date cursorAt, Pageable pageable);

}
