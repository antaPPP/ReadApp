package com.readapp.backend.models.utils;

public class SignUpForm {
    private String countryCode;
    private String mobile;
    private String verificationCode;
    private String password;

    public String getCountryCode() {
        return countryCode;
    }

    public SignUpForm setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public SignUpForm setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public SignUpForm setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignUpForm setPassword(String password) {
        this.password = password;
        return this;
    }
}