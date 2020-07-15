package com.readapp.backend.services;

import com.readapp.backend.dto.ActivityResponse;
import com.readapp.backend.models.Activity;
import com.readapp.backend.models.http.ActivityForm;

import java.util.List;

public interface ActivityService {
    Activity addActivity(ActivityForm form);
    Integer getActivitiesCount(Long uid, String type, Long cursorAt);

    List<ActivityResponse> getLikeActivities(Long uid, int page, int capacity);
    List<ActivityResponse> getCommentActivities(Long uid, int page, int capacity);
    List<ActivityResponse> getRateActivities(Long uid, int page, int capacity);
    List<ActivityResponse> getFollowerActivities(Long uid, int page, int capacity);
}
