package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.UserDao;
import com.readapp.backend.services.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("ForgetPasswordService")
public class ForgetPasswordServiceImpl implements ForgetPasswordService {
    @Autowired
    UserDao userDao;

    @Override
    public void setNewPassword(String countryCode, String mobile, String newPassword) {
        userDao.updatePassword(newPassword, countryCode, mobile);
    }

}