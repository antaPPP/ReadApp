package com.readapp.backend.dao;

import com.readapp.backend.models.Profile;
import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile, Long> {

    @Query(value = "select p from Profile p where p.user = ?1")
    Profile findByUserId(User user);

    @Query(value = "SELECT p FROM Profile p WHERE p.nickname LIKE CONCAT('%',?1,'%') ")
    Page<Profile> searchByKeyword(String keyword, Pageable pageable);
}
