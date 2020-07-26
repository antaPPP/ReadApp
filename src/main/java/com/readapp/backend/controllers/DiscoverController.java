package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.RecentActivityService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscoverController {
    @Autowired
    RecentActivityService recentActivityService;

    @GetMapping(value = "/recent_activities")
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getRecentActivities(@RequestHeader("Authorization")String Authorization,
                                        @RequestParam("page") int page,
                                        @RequestParam("capacity") int capacity) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            return Response.success(recentActivityService.getRecentActivities(uid, page, capacity));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/recent_activity/like")
    @RequiresAuthentication
    @AutoRefreshToken
    public Response addRecentActivityLike(@RequestHeader("Authorization") String Authorization,
                                          @RequestParam("id") Long id) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            recentActivityService.addRecentActivityLike(uid, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

    @DeleteMapping(value = "/recent_activity/like")
    @RequiresAuthentication
    @AutoRefreshToken
    public Response deleteRecentActivityLike(@RequestHeader("Authorization") String Authorization,
                                             @RequestParam("id") Long id) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            recentActivityService.deleteRecentActivityLike(uid, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

}
