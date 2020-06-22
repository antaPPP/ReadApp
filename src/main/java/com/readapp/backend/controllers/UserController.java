package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.models.utils.ProfileForm;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JSONUtils;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @RequiresAuthentication
    @SuppressWarnings("all")
    public Response createProfile(@RequestBody ProfileForm form, @RequestHeader("Authorization") String Authorization){
        System.out.println(JSONUtils.objectToJson(form));
        try {
            userService.createUserProfile(Long.parseLong(JWTUtil.getUserId(Authorization)), form);
        } catch (Exception e){
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getUserProfile(@RequestParam("id") String id, @RequestHeader("Authorization") String Authorization) {
        ProfileForm form = null;
        form = userService.getUserProfile(Long.parseLong(id));
        return Response.success(form);
    }


}
