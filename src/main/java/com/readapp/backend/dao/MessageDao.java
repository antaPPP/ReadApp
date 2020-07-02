package com.readapp.backend.dao;

import com.readapp.backend.models.Chat;
import com.readapp.backend.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message, Long> {

    @Query(value = "SELECT m FROM Message m WHERE m.chat = ?1")
    Page<Message> findMessagesByChat(Chat chat, Pageable pageable);
}
