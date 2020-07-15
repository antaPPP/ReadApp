package com.readapp.backend.serviceImpls;

import com.alibaba.fastjson.JSONObject;
import com.readapp.backend.dao.ProfileDao;
import com.readapp.backend.dao.TagDao;
import com.readapp.backend.dao.UserDao;
import com.readapp.backend.dto.UserResponse;
import com.readapp.backend.models.Profile;
import com.readapp.backend.models.Tag;
import com.readapp.backend.models.User;
import com.readapp.backend.models.http.ActivityForm;
import com.readapp.backend.models.utils.ProfileForm;
import com.readapp.backend.services.ActivityService;
import com.readapp.backend.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    ProfileDao profileDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    ActivityService activityService;

    @Override
    public ProfileForm getUserProfile(Long uid) {
        Profile profile = profileDao.findByUserId(new User().setId(uid));
        List<Tag> tags = tagDao.findByUserId(new User().setId(uid));
        ProfileForm form = new ProfileForm(profile).setTags(new ArrayList<>());
        for (Tag t : tags) {
            form.getTags().add(t.getContent());
        }
        return form;
    }

    @Override
    public void createUserProfile(Long uid, @NotNull ProfileForm form) throws Exception{
        Profile profile = profileDao.findByUserId(new User().setId(uid));
        if (profile == null) profile = new Profile();
        profile.setAvatarUrl(form.getAvatarUrl().isEmpty() ? profile.getAvatarUrl() : form.getAvatarUrl())
                .setCoverUrl(form.getCoverUrl().isEmpty() ? profile.getCoverUrl() : form.getCoverUrl())
                .setNickname(form.getNickname().isEmpty() ? profile.getNickname() : form.getNickname())
                .setWhatsup(form.getWhatsup().isEmpty() ? profile.getWhatsup() : form.getWhatsup());
        Optional<User> optionalUser = userDao.findById(uid);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            for (String t : form.getTags()){
                Tag tag = new Tag();
                tag.setContent(t);
                tag.setUser(user);
                System.out.println(t);
                tagDao.save(tag);
            }
            user.setProfile(profile);
            profile.setUser(user);
            userDao.save(user);
        }
    }

    @Override
    public List<UserResponse> searchByKeyword(String keyword, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page - 1,size);
        Page<User> users = userDao.searchByKeyword(keyword, pageable);
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users.toList()) {
            responses.add(new UserResponse(user));
        }
        System.out.println(JSONObject.toJSON(responses));
        return responses;
    }

    @Override
    public User getUser(Long uid) {
        Optional<User> optionalUser = userDao.findById(uid);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return user;
        }
        return null;
    }

    @Override
    public List<Long> getFollowingIDs(Long uid) throws Exception {
        List<User> followingList = userDao.findByFollowers_Id(uid);
        List<Long> ids = new ArrayList<>();
        for (User u : followingList) {
            ids.add(u.getId());
        }
        return ids;
    }

    @Override
    @Transactional
    public void follow(Long fromUser, Long toUser) throws Exception {
        User from = userDao.getOne(fromUser);
        User to = userDao.getOne(toUser);
        if (to == null) throw new NoSuchElementException();
        if (from.getFollows().contains(to) || to.getFollowers().contains(from)) return;
        from.getFollows().add(to);
        from.setFollowCount(from.getFollowCount() + 1);
        to.setFollowerCount(to.getFollowerCount() + 1);
        userDao.save(to);
        userDao.save(from);
        ActivityForm form = new ActivityForm();
        form.setType("follower");
        form.setFollower(from);
        form.setToUser(toUser);
        activityService.addActivity(form);
    }

    /**
     * 取消关注
     * @param fromUser
     * @param toUser
     * @throws Exception
     */
    @Override
    public void disFollow(Long fromUser, Long toUser) throws Exception {
        User from = userDao.getOne(fromUser);
        User to = userDao.getOne(toUser);
        if (to == null) throw new NoSuchElementException();
        if (!from.getFollows().contains(to)) return;
        for (int i = from.getFollows().size() - 1; i >= 0; i--)  {
            if (from.getFollows().get(i).equals(to)) {
                from.getFollows().remove(i);
            }
        }
        from.setFollowCount(from.getFollowCount() - 1);
        to.setFollowerCount(to.getFollowerCount() - 1);
        userDao.save(from);
        userDao.save(to);
    }
}
