package com.readapp.backend.services;

public interface ForgetPasswordService {
    void setNewPassword(String countryCode, String mobile, String newPassword);
}
