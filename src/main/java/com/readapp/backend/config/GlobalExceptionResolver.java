package com.readapp.backend.config;

import com.readapp.backend.exceptions.TokenExpiredException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("Exception captured");
        e.printStackTrace();
        if (e instanceof AuthenticationException) {
            System.out.println("AAAAA: ASNWEIUNFIUWEONFIUWENF");
        }
        return null;
    }
}
