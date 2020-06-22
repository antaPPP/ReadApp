package com.readapp.backend.dao;

import com.readapp.backend.models.Tag;
import com.readapp.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao extends JpaRepository<Tag, Long> {
    @Query(value = "select t from Tag t where t.user = ?1")
    List<Tag> findByUserId(User user);
}
