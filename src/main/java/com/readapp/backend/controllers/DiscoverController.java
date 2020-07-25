package com.readapp.backend.controllers;

import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.RecentActivityService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoverController {
    @Autowired
    RecentActivityService recentActivityService;

    @GetMapping(value = "/recent_activities")
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getRecentActivities(@RequestHeader("Authorization")String Authorization,
                                        @RequestParam("cursorAt") Long cursorAt,
                                        @RequestParam("page") int page,
                                        @RequestParam("capacity") int capacity) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }


}
