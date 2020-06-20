package com.readapp.backend.dao;

import com.readapp.backend.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepository<Tag, Long> {
}
