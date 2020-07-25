package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.User;
import com.readapp.backend.services.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("ForgetPasswordService")
public class ForgetPasswordServiceImpl implements ForgetPasswordService {
    @Autowired
    UserDao userDao;

    @Override
    public User setNewPassword(String countryCode, String mobile, String newPassword) {
        User user = userDao.findByMobile(countryCode, mobile);
        if (newPassword.equals(user.getPassword())) {
            throw new IllegalArgumentException();
        }

        user.setPassword(newPassword);
        userDao.updatePassword(newPassword, countryCode, mobile);
        userDao.save(user);

        return user;
    }
}