package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.ActivityDao;
import com.readapp.backend.models.Activity;
import com.readapp.backend.models.User;
import com.readapp.backend.models.http.ActivityForm;
import com.readapp.backend.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityDao activityDao;

    @Override
    public Activity addActivity(ActivityForm form) {
        if (form.getType().equals("like")) {
            Activity activity = new Activity();
            activity.setType("like");
            activity.setFromLike(form.getLike());
            activity.setUser(new User().setId(form.getToUser()));
            activity = activityDao.save(activity);
            return activity;
        } else if (form.getType().equals("comment")) {
            Activity activity = new Activity();
            activity.setType("comment");
            activity.setFromComment(form.getComment());
            activity.setUser(new User().setId(form.getToUser()));
            activity = activityDao.save(activity);
            return activity;
        } else if (form.getType().equals("follower")) {
            Activity activity = new Activity();
            activity.setType("follower");
            activity.setFromUser(form.getFollower());
            activity.setUser(new User().setId(form.getToUser()));
            activity = activityDao.save(activity);
            return activity;
        } else if (form.getType().equals("reply")) {
            Activity activity = new Activity();
            activity.setFromReply(form.getReply());
            activity.setType("comment");
            activity.setUser(new User().setId(form.getToUser()));
            activity = activityDao.save(activity);
            return activity;
        } else if (form.getType().equals("rate")) {
            /**
             * TODO: rate
             */
        }
        return null;
    }

    @Override
    public List<Activity> getLikeActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "like", pageable);
        return activities.toList();
    }

    @Override
    public List<Activity> getCommentActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "comment", pageable);
        return activities.toList();
    }

    @Override
    public List<Activity> getRateActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "rate", pageable);
        return activities.toList();
    }

    @Override
    public List<Activity> getFollowerActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "follower", pageable);
        return activities.toList();
    }
}
