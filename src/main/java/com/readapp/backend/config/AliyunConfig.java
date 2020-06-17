package com.readapp.backend.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.readapp.aliyun")
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

    @Override
    public void afterPropertiesSet() throws Exception {

        ACCESS_KEY_ID = this.accessKeyId;

        ACCESS_KEY_SECRET = this.accessKeySecret;

        SIGN_NAME = this.smsSignName;

        VERIFICATION_SMS_TEMPLATE = this.verificationSMSTemplate;

    }
}
