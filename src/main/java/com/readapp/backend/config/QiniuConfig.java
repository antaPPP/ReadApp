package com.readapp.backend.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "readapp.qiniu")
public class QiniuConfig implements InitializingBean {
    private String accessKey;
    private String accessSecret;
    private String url;
    private String bucket;
    private int expires;

    public static String ACCESS_KEY;
    public static String ACCESS_SECRET;
    public static String URL;
    public static String BUCKET;
    public static int EXPIRES;

    public String getAccessKey() {
        return accessKey;
    }

    public QiniuConfig setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public QiniuConfig setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public QiniuConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getBucket() {
        return bucket;
    }

    public QiniuConfig setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public int getExpires() {
        return expires;
    }

    public QiniuConfig setExpires(int expires) {
        this.expires = expires;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY = this.accessKey;
        ACCESS_SECRET = this.accessSecret;
        URL = this.url;
        BUCKET = this.bucket;
        EXPIRES = this.expires;
    }
}
