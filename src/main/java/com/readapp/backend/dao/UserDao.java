package com.readapp.backend.dao;

import com.readapp.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.countryCode = ?1 AND u.mobile = ?2")
    User findByMobile(String countryCode, String mobile);

    List<User> findByFollowers_Id(Long id);

}
