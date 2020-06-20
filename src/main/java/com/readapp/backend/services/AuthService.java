package com.readapp.backend.services;

import com.readapp.backend.models.User;
import com.readapp.backend.models.utils.SignUpForm;


public interface AuthService {
    User signUp(SignUpForm form);
    void requestVerificationCode(String countryCode, String mobile);
    boolean verifyCode(String countryCode, String mobile, String code);
    User createUser(User user);
}
