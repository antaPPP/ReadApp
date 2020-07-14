package com.readapp.backend.services;

import com.readapp.backend.models.Activity;
import com.readapp.backend.models.http.ActivityForm;

import java.util.List;

public interface ActivityService {
    Activity addActivity(ActivityForm form);
    List<Activity> getLikeActivities(Long uid, int page, int capacity);
    List<Activity> getCommentActivities(Long uid, int page, int capacity);
    List<Activity> getRateActivities(Long uid, int page, int capacity);
    List<Activity> getFollowerActivities(Long uid, int page, int capacity);
}
