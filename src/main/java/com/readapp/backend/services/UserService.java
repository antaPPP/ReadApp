package com.readapp.backend.services;

import com.readapp.backend.models.User;
import com.readapp.backend.models.utils.ProfileForm;

public interface UserService {
    ProfileForm getUserProfile(Long uid);
    void createUserProfile(Long uid, ProfileForm form) throws Exception;
    User getUser(Long uid);
}
