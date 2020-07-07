package com.readapp.backend.dao;

import com.readapp.backend.models.Comment;
import com.readapp.backend.models.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyDao extends JpaRepository<Reply, Long> {

    @Query(value = "SELECT r FROM Reply r WHERE r.toComment = ?1")
    Page<Reply> findByComment(Comment comment, Pageable pageable);
}
