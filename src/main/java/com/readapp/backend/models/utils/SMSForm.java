package com.readapp.backend.models.utils;

public class SMSForm {
    private String mobile;
    private String action;
    private String outId;
    private String extendCode;
    private String code;

    public String getMobile() {
        return mobile;
    }

    public SMSForm setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAction() {
        return action;
    }

    public SMSForm setAction(String action) {
        this.action = action;
        return this;
    }

    public String getOutId() {
        return outId;
    }

    public SMSForm setOutId(String outId) {
        this.outId = outId;
        return this;
    }

    public String getExtendCode() {
        return extendCode;
    }

    public SMSForm setExtendCode(String extendCode) {
        this.extendCode = extendCode;
        return this;
    }

    public String getCode() {
        return code;
    }

    public SMSForm setCode(String code) {
        this.code = code;
        return this;
    }
}
