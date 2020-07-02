package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.FileService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/service/qiniu/upload_token", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getToken(@RequestHeader("Authorization") String Authorization) {

        try {
            Map<String, String> res = new HashMap<>();
            res.put("token", fileService.getToken());
            return Response.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
