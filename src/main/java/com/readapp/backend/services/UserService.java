package com.readapp.backend.services;

import com.readapp.backend.dto.UserResponse;
import com.readapp.backend.models.User;
import com.readapp.backend.models.utils.ProfileForm;

import java.util.List;

public interface UserService {
    ProfileForm getUserProfile(Long uid);
    void createUserProfile(Long uid, ProfileForm form) throws Exception;
    User getUser(Long uid);
    List<UserResponse> searchByKeyword(String keyword, int page, int size) throws Exception;
    List<Long> getFollowingIDs(Long uid) throws Exception;
    void follow(Long fromUser, Long toUser) throws Exception;
    void disFollow(Long fromUser, Long toUser) throws Exception;
}
