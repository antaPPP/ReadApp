package com.readapp.backend.aop;

import com.readapp.backend.dao.UserDao;
import com.readapp.backend.exceptions.InconsistantParamsException;
import com.readapp.backend.models.User;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.utils.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Aspect
public class TokenAspect {

    @Autowired
    UserDao userDao;


    @Pointcut("@annotation(com.readapp.backend.modules.annotations.AutoRefreshToken)")
    public void annotationPointCut(){

    }

    @Before("annotationPointCut()")
    public void beforePointcut(JoinPoint joinPoint) {
        // 此处进入到方法前  可以实现一些业务逻辑
    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] params = methodSignature.getParameterNames();// 获取参数名称
        Object[] args = joinPoint.getArgs();
        int index = -1;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("Authorization")) index = i;
        }
        if (index == -1) throw new InconsistantParamsException();

        String token = String.valueOf(args[index]);
        if (JWTUtil.checkTokenExpired(token)) {
            Optional<User> optionalUser = userDao.findById(Long.parseLong(JWTUtil.getUserId(token)));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Map<String, String> map = new HashMap<>();
                map.put("flag", "useNewToken");
                map.put("token", JWTUtil.sign(String.valueOf(user.getId()), user.getPassword()));
                return Response.success(map);
            }
        }

        return joinPoint.proceed();
    }

    /**
     * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     * @param joinPoint
     */
    @AfterReturning("annotationPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
    }

}
