package com.readapp.backend.models.http;

public enum HttpStatus {
    SUCCESS("200", "成功"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
    ;


    private String resultCode;

    private String message;

    HttpStatus(String resultCode, String message){
        this.resultCode = resultCode;
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public HttpStatus setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus setMessage(String message) {
        this.message = message;
        return this;
    }
}
