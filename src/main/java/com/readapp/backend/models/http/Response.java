package com.readapp.backend.models.http;

public class Response {
    private String status;
    private String message;
    private Object data;

    public Response(){}

    public String getStatus() {
        return status;
    }

    public Response setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public static Response success(Object data) {
        Response response = new Response();
        response.setData(data);
        response.setMessage(HttpStatus.SUCCESS.getMessage());
        response.setStatus(HttpStatus.SUCCESS.getResultCode());
        return response;
    }

    public static Response simple(HttpStatus status, Object data){
        Response response = new Response();
        response.setStatus(status.getResultCode());
        response.setMessage(status.getMessage());
        response.setData(data);
        return response;
    }
}
