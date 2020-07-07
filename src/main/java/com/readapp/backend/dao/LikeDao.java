package com.readapp.backend.dao;

import com.readapp.backend.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<Like, Long> {
}
