package com.readapp.backend.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "readapp.aliyun")
public class AliyunConfig implements InitializingBean {

    /**
     * Aliyun related configuration
     */

    private String accessKeyId;

    private String accessKeySecret;

    private String smsSignName;

    private String verificationSMSTemplate;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    public static String SIGN_NAME;

    public static String VERIFICATION_SMS_TEMPLATE;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public AliyunConfig setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public AliyunConfig setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public static String getSignName() {
        return SIGN_NAME;
    }

    public static void setSignName(String signName) {
        SIGN_NAME = signName;
    }

    public static String getVerificationSmsTemplate() {
        return VERIFICATION_SMS_TEMPLATE;
    }

    public static void setVerificationSmsTemplate(String verificationSmsTemplate) {
        VERIFICATION_SMS_TEMPLATE = verificationSmsTemplate;
    }

    public String getSmsSignName() {
        return smsSignName;
    }

    public AliyunConfig setSmsSignName(String smsSignName) {
        this.smsSignName = smsSignName;
        return this;
    }

    public String getVerificationSMSTemplate() {
        return verificationSMSTemplate;
    }

    public AliyunConfig setVerificationSMSTemplate(String verificationSMSTemplate) {
        this.verificationSMSTemplate = verificationSMSTemplate;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        ACCESS_KEY_ID = this.accessKeyId;

        ACCESS_KEY_SECRET = this.accessKeySecret;

        SIGN_NAME = this.smsSignName;

        VERIFICATION_SMS_TEMPLATE = this.verificationSMSTemplate;

    }
}
