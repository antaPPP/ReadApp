package com.readapp.backend.services;

import com.readapp.backend.models.Profile;
import com.readapp.backend.models.User;

public interface UserService {
    Profile getUserProfile(Long uid);
    User getUser(Long uid);

}
