package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.UserDao;
import com.readapp.backend.exceptions.DuplicationEntityException;
import com.readapp.backend.exceptions.InconsistantParamsException;
import com.readapp.backend.exceptions.InvalidSecretException;
import com.readapp.backend.models.Profile;
import com.readapp.backend.models.User;
import com.readapp.backend.models.http.AuthForm;
import com.readapp.backend.models.utils.SMSForm;
import com.readapp.backend.models.utils.SignUpForm;
import com.readapp.backend.services.AuthService;
import com.readapp.backend.services.SmsService;
import com.readapp.backend.utils.CoderUtils;
import com.readapp.backend.utils.JWTUtil;
import com.readapp.backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    SmsService smsService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserDao userDao;

    @Override
    public User signUp(SignUpForm form) {
        String code = (String)redisUtil.get("verify." + form.getCountryCode() + form.getMobile());
        if (!code.equals(form.getVerificationCode())) {
            throw new InconsistantParamsException();
        }
        User user = userDao.findByMobile(form.getCountryCode(), form.getMobile());
        if (user != null) {
            throw new DuplicationEntityException();
        }
        if (CoderUtils.checkPassword(form.getPassword())) {
            throw new InvalidSecretException();
        }

        user = new User();
        user.setArticles(new ArrayList<>());
        user.setBlocked(false);
        user.setCountryCode(form.getCountryCode());
        user.setMobile(form.getMobile());
        user.setPassword(form.getPassword());
        user.setProfile(new Profile());
        user.setPermissions("USER");

        userDao.save(user);

        return user;
    }

    @Override
    public void requestVerificationCode(String countryCode, String mobile) {
        SMSForm form = new SMSForm();
        form.setMobile(countryCode.trim() + mobile.trim());
        String code = smsService.sendVerificationSMS(form);
        redisUtil.set("verify." + countryCode + mobile, code, 600L);
    }

    @Override
    public boolean verifyCode(String countryCode, String mobile, String code) {
        String cachedCode = (String)redisUtil.get("verify." + countryCode + mobile);
        return cachedCode == null || !cachedCode.equals(code);
    }

    @Override
    public AuthForm loginByPassword(String countryCode, String mobile, String password) {
        User user = userDao.findByMobile(countryCode, mobile);
        if (user == null || !user.getPassword().equals(password)) {
            throw new InvalidSecretException();
        }

        return new AuthForm().setId(user.getId()).setToken(JWTUtil.sign(String.valueOf(user.getId()), user.getPassword()));
    }

    @Override
    public String loginBySms(String countryCode, String mobile, String code) {
        String vcode = (String)redisUtil.get("verify." + countryCode + mobile);
        if (!code.equals(vcode)) throw new InvalidSecretException();
        User user = userDao.findByMobile(countryCode, mobile);
        if (user == null) throw new InvalidSecretException();
        return JWTUtil.sign(String.valueOf(user.getId()), user.getPassword());
    }

    @Override
    public String refreshToken(String expiredToken) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
