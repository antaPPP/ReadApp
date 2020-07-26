package com.readapp.backend.services;

import com.readapp.backend.models.User;

public interface ForgetPasswordService {
    User setNewPassword(String countryCode, String mobile, String newPassword);
}
