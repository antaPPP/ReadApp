package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.ProfileDao;
import com.readapp.backend.dao.TagDao;
import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.Profile;
import com.readapp.backend.models.Tag;
import com.readapp.backend.models.User;
import com.readapp.backend.models.utils.ProfileForm;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JSONUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (profile == null) throw new NoSuchElementException();
        profile.setAvatarUrl(form.getAvatarUrl().isEmpty() ? profile.getAvatarUrl() : form.getAvatarUrl())
                .setCoverUrl(form.getCoverUrl().isEmpty() ? profile.getCoverUrl() : form.getCoverUrl())
                .setNickname(form.getNickname().isEmpty() ? profile.getNickname() : form.getNickname())
                .setWhatsup(form.getWhatsup().isEmpty() ? profile.getWhatsup() : form.getWhatsup());
        if (form.getTags() == null || form.getTags().isEmpty()) throw new NoSuchElementException();
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
        }
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
}
