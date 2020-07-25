package com.readapp.backend.services;

import com.readapp.backend.dto.RecentActivityResponse;

import java.util.List;

public interface RecentActivityService {
    List<RecentActivityResponse> getRecentActivities(Long uid, int page, int capacity) throws Exception;
}
