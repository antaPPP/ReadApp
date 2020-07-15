package com.readapp.backend.dao;

import com.readapp.backend.models.Activity;
import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDao extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT a FROM Activity a WHERE a.user = ?1 AND a.type = ?2")
    Page<Activity> findByType(User user, String type, Pageable pageable);
}
