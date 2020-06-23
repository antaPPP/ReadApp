package com.readapp.backend.dao;

import com.readapp.backend.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatDao extends JpaRepository<Chat, Long> {



}
