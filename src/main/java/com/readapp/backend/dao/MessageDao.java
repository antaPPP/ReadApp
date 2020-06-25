package com.readapp.backend.dao;

import com.readapp.backend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message, Long> {

}
