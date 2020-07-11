package com.readapp.backend.dao;

import com.readapp.backend.models.Chat;
import com.readapp.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDao extends JpaRepository<Chat, Long> {

    @Query(value = "select c.members from Chat c where c.id = ?1")
    List<User> findMembersId(Long cid);

    List<Chat> findByMembers_Id(Long id);
}
