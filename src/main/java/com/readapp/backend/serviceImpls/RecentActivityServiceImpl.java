package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.*;
import com.readapp.backend.dto.RecentActivityResponse;
import com.readapp.backend.models.*;
import com.readapp.backend.models.http.ReplyForm;
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
    LikeDao likeDao;
    @Autowired
    ActivityDao activityDao;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserDao userDao;
    @Autowired
    ReplyDao replyDao;
    @Autowired
    ProfileDao profileDao;

    @Override
    public List<RecentActivityResponse> getRecentActivities(Long uid, int page, int capacity) throws Exception {
        List<Object> objects = redisUtil.range("ras:" + uid,  (long)(page - 1) * capacity, (long)page * capacity);
        if (objects == null) return null;
        List<RecentActivityResponse> activities = new ArrayList<>();
        for (Object id : objects) {
            Long aid = ((Integer)id).longValue();
            // Cast
            RecentActivity activity = recentActivityDao.getOne(aid);
            if (activity != null){
                boolean liked = likeDao.findActiveByUserAndRecentActivity(new User().setId(uid), activity) != null;
                activities.add(new RecentActivityResponse(activity));
            }
        }
        return activities;
    }

    @Override
    public void addRecentActivityLike(Long uid, Long activityId) throws Exception {
        User user = userDao.getOne(uid);
        if (user == null) return;

        RecentActivity activity = recentActivityDao.getOne(activityId);
        if (activity == null) return;

        Like like = likeDao.findByUserAndRecentActivity(user, activity);

        if (like != null) {
            if (!like.isDislike()) return;
            like.setDislike(false);
            likeDao.save(like);
            activity.setLikeCount(activity.getLikeCount() + 1);
            recentActivityDao.save(activity);
            return;
        }

        like = new Like().setFromUser(user).setToRecentActivity(activity).setDislike(false);
        likeDao.save(like);
        Activity notification = new Activity().setFromUser(user).setType("recentActivity").setFromRecentActivity(activity);
        activityDao.save(notification);
        recentActivityDao.save(activity.setLikeCount(activity.getLikeCount() + 1));

    }

    @Override
    public void deleteRecentActivityLike(Long uid, Long activityId) throws Exception {
        Like like = likeDao.findByUserAndRecentActivity(new User().setId(uid), new RecentActivity().setId(activityId));

        if (like != null) {
            if (like.isDislike()) return;
            RecentActivity activity = recentActivityDao.getOne(activityId);
            like.setDislike(true);
            likeDao.save(like);
            activity.setLikeCount(activity.getLikeCount() - 1);
            recentActivityDao.save(activity);
        }
    }

    @Override
    public Reply addReply(ReplyForm form) throws Exception {
        if (!recentActivityDao.existsById(form.getToRecentActivity())) return null;

        Reply reply = new Reply();
        reply.setFromUser(new User().setId(form.getFromUser()));
        reply.setContent(form.getContent());
        reply.setToRecentActivity(new RecentActivity().setId(form.getToRecentActivity()));
        if (form.getToReply() != null) reply.setToReply(replyDao.getOne(form.getToReply()));
        reply = replyDao.save(reply);
        reply.getFromUser().setProfile(profileDao.findByUserId(new User().setId(form.getFromUser())));

        return reply;
    }

}
