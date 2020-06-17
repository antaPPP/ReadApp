package com.readapp.backend.services;

import com.readapp.backend.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class SignUpForm {
    private String countryCode;
    private String mobile;
    private String verificationCode;
    private String password;
}

public interface AuthService {
    User signUp(SignUpForm form);
    User createUser(User user);
}
