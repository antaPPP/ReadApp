package com.readapp.backend.controllers;

import com.readapp.backend.dto.UserResponse;
import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.models.utils.ProfileForm;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JSONUtils;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@SuppressWarnings("all")
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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Response searchUsers(@RequestParam("keyword") String keyword, @RequestParam("page") int page, @RequestParam("size") int size){
        try {
            return Response.success(userService.searchByKeyword(keyword, page, size));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/user/followings", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getFollowing(@RequestHeader("Authorization") String Authorization,
                                 @RequestParam(value = "detail", defaultValue = "false") boolean detail,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "12") int size) {
        Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
        try {
            if (!detail) {
                return Response.success(userService.getFollowingIDs(uid));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(new ArrayList<>());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Response getUserDetail(@RequestParam("id") Long id) {
        try {
            return Response.success(new UserResponse(userService.getUser(id)).setPermissions(null));
        } catch (Exception e) {
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/user/followings", method = RequestMethod.PATCH)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response patchFollowing(@RequestParam("to") Long to, @RequestHeader("Authorization") String Authorization) {
        try {
            Long id = Long.parseLong(JWTUtil.getUserId(Authorization));
            userService.follow(id, to);
            return Response.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/user/followings", method = RequestMethod.DELETE)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response cancelFollowing(@RequestParam("to") Long to, @RequestHeader("Authorization") String Authorization) {
        try {
            Long id = Long.parseLong(JWTUtil.getUserId(Authorization));
            System.out.println("10101");
            userService.disFollow(id, to);
            return Response.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
