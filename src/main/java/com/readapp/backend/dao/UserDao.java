package com.readapp.backend.dao;

import com.readapp.backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.countryCode = ?1 AND u.mobile = ?2")
    User findByMobile(String countryCode, String mobile);

    List<User> findByFollowers_Id(Long id);

    @Query(value = "SELECT u FROM Profile p, User u WHERE p.nickname LIKE CONCAT('%',?1,'%') AND u.profile = p ")
    Page<User> searchByKeyword(String keyword, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.password = ?1 WHERE u.id = ?2")
    void updatePassword(String password, Long id);
}
