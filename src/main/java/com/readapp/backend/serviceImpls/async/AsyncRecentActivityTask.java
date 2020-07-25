package com.readapp.backend.serviceImpls.async;

import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.RecentActivity;
import com.readapp.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AsyncRecentActivityTask {

    @Autowired
    UserDao userDao;
    @Autowired
    RedisUtil redisUtil;

    @Async
    public void insertRecentActivities(RecentActivity recentActivity) {
        List<Long> followerIds = userDao.findFollowerIds(recentActivity.getFromUser().getId());
        for (Long id : followerIds) {
            redisUtil.lpush("ras:" + id, recentActivity.getId());
        }
    }

}
