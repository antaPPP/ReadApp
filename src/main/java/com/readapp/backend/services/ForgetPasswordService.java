package com.readapp.backend.services;

public interface ForgetPasswordService {
    void setNewPassword(Long username, String newPassword);
}
