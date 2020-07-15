package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.ActivityService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @GetMapping(value = "/activities")
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getActivities(@RequestHeader("Authorization")String Authorization,
                                  @RequestParam("type") String type,
                                  @RequestParam("page") int page,
                                  @RequestParam("capacity") int capacity) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            if (type.equals("like")) {
                return Response.success(activityService.getLikeActivities(uid, page, capacity));
            } else if (type.equals("comment")) {
                return Response.success(activityService.getCommentActivities(uid, page, capacity));
            } else if (type.equals("follower")) {
                return Response.success(activityService.getFollowerActivities(uid, page, capacity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

    @GetMapping(value = "/activities_count")
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getActivitiesCount(
            @RequestHeader("Authorization") String Authorization,
            @RequestParam("type") String type,
            @RequestParam("cursorAt") Long cursorAt
    ) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            return Response.success(activityService.getActivitiesCount(uid, type, cursorAt));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
