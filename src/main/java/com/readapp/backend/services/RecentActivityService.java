package com.readapp.backend.services;

import com.readapp.backend.dto.RecentActivityResponse;
import com.readapp.backend.models.Reply;
import com.readapp.backend.models.http.ReplyForm;

import java.util.List;

public interface RecentActivityService {
    List<RecentActivityResponse> getRecentActivities(Long uid, int page, int capacity) throws Exception;

    void addRecentActivityLike(Long uid, Long activityId) throws Exception;

    void deleteRecentActivityLike(Long uid, Long activityId) throws Exception;

    Reply addReply(ReplyForm form) throws Exception;
}
