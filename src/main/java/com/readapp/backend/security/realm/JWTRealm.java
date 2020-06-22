package com.readapp.backend.security.realm;

import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.User;
import com.readapp.backend.security.JWTToken;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JWTRealm extends AuthorizingRealm {
    /**
     * 用于处理用户是否合法的realm
     */

    @Autowired
    UserDao userDao;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String id = JWTUtil.getUserId(principals.toString());
        User user = userDao.findById(Long.parseLong(id)).get();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getPermissions());
        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermissions().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        //Get token
        String token = (String) auth.getCredentials();

        // 解密获得id，用于和数据库进行对比
        String id = JWTUtil.getUserId(token);
        if (id == null) {
            throw new AuthenticationException("token invalid");
        }

        Optional<User> opt = userDao.findById(Long.parseLong(id));

        if (!opt.isPresent()) {
            throw new AuthenticationException("User didn't existed!");
        }

        User userBean = opt.get();


        if (! JWTUtil.verify(token, id, userBean.getPassword())) {

            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
