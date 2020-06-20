package com.readapp.backend.dao;

import com.readapp.backend.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile, Long> {
}
