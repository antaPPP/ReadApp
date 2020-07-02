package com.readapp.backend.exceptions.handlers;

import com.readapp.backend.exceptions.InconsistantParamsException;
import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class AuthenticationHandler {

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Response refreshToken(UnauthenticatedException e){
        System.out.println("UE captured");
        return Response.simple(HttpStatus.SIGNATURE_NOT_MATCH, null);
    }

    @ExceptionHandler(InconsistantParamsException.class)
    @ResponseBody
    public Response inconsist(InconsistantParamsException e) {
        e.printStackTrace();
        return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response unknownExceptionHandler(Exception e) {
        e.printStackTrace();
        return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    }

}
