package com.readapp.backend.services;

import com.readapp.backend.models.User;
import com.readapp.backend.models.http.AuthForm;
import com.readapp.backend.models.utils.SignUpForm;


public interface AuthService {
    User signUp(SignUpForm form);
    void requestVerificationCode(String countryCode, String mobile);
    String refreshToken(String expiredToken);
    boolean verifyCode(String countryCode, String mobile, String code);
    User createUser(User user);
    AuthForm loginByPassword(String countryCode, String mobile, String password);
    String loginBySms(String countryCode, String mobile, String code);
}
