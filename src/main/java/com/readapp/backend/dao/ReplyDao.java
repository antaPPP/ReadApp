package com.readapp.backend.dao;

import com.readapp.backend.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyDao extends JpaRepository<Reply, Long> {
}
