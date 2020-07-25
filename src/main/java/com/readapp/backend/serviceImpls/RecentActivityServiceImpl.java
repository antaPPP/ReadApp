package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.RecentActivityDao;
import com.readapp.backend.dto.RecentActivityResponse;
import com.readapp.backend.models.RecentActivity;
import com.readapp.backend.services.RecentActivityService;
import com.readapp.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("recentActivity")
public class RecentActivityServiceImpl implements RecentActivityService {

    @Autowired
    RecentActivityDao recentActivityDao;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<RecentActivityResponse> getRecentActivities(Long uid, int page, int capacity) throws Exception {
        List<Object> objects = redisUtil.range("ras:" + uid,  (long)(page - 1) * capacity, (long)page * capacity);
        if (objects == null) return null;
        List<RecentActivityResponse> activities = new ArrayList<>();
        for (Object id : objects) {
            Long aid = (long) id;
            // Cast
            RecentActivity activity = recentActivityDao.getOne(aid);
            if (activity != null) activities.add(new RecentActivityResponse(activity));
        }
        return activities;
    }

}
