package com.readapp.backend.dao;

import com.readapp.backend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
