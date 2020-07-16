package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.ActivityDao;
import com.readapp.backend.dao.LikeDao;
import com.readapp.backend.dto.ActivityResponse;
import com.readapp.backend.models.Activity;
import com.readapp.backend.models.User;
import com.readapp.backend.models.http.ActivityForm;
import com.readapp.backend.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityDao activityDao;
    LikeDao likeDao;

    @Override
    public Activity addActivity(ActivityForm form) {
        Activity activity = new Activity();

        if (form.getType().equals("like")) {
            User user = new User().setId(form.getToUser());
            activity.setType("like");
            activity.setFromLike(form.getLike());
            activity.setUser(user);
            if(likeDao.findByArticleUserAndType(user, "like", form.getLike().getToArticle()) != null) {
                activity = activityDao.save(activity);
                return activity;
            }
            return null;
        } else if (form.getType().equals("comment")) {
            activity.setType("comment");
            activity.setFromComment(form.getComment());
            activity.setUser(new User().setId(form.getToUser()));
            activity = activityDao.save(activity);
            return activity;
        } else if (form.getType().equals("follower")) {
            activity.setType("follower");
            activity.setFromUser(form.getFollower());
            activity.setUser(new User().setId(form.getToUser()));
            activity = activityDao.save(activity);
            return activity;
        } else if (form.getType().equals("reply")) {
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
    public Integer getActivitiesCount(Long uid, String type, Long cursorAt) {
        return activityDao.countByCreatedAt(new User().setId(uid), type, new Date(cursorAt));
    }

    @Override
    public List<ActivityResponse> getLikeActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page - 1, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "like", pageable);
        System.out.println(activities.toList().size());
        List<ActivityResponse> responses = new ArrayList<>();
        for (Activity activity : activities) {
            responses.add(new ActivityResponse(activity));
        }
        return responses;
    }

    @Override
    public List<ActivityResponse> getCommentActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page - 1, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "comment", pageable);
        List<ActivityResponse> responses = new ArrayList<>();
        for (Activity activity : activities) {
            responses.add(new ActivityResponse(activity));
        }
        return responses;
    }

    @Override
    public List<ActivityResponse> getRateActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page - 1, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "rate", pageable);
        List<ActivityResponse> responses = new ArrayList<>();
        for (Activity activity : activities) {
            responses.add(new ActivityResponse(activity));
        }
        return responses;
    }

    @Override
    public List<ActivityResponse> getFollowerActivities(Long uid, int page, int capacity) {
        Pageable pageable = PageRequest.of(page - 1, capacity);
        Page<Activity> activities = activityDao.findByType(new User().setId(uid), "follower", pageable);
        List<ActivityResponse> responses = new ArrayList<>();
        for (Activity activity : activities) {
            responses.add(new ActivityResponse(activity));
        }
        return responses;
    }
}
