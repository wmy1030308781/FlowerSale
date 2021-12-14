package com.xxx.aop;

import com.xxx.annotation.PermissionProxy;
import com.xxx.exceptions.NoLoginException;
import com.xxx.exceptions.PermissionException;
import com.xxx.utils.LoginUserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.List;

@Aspect
@Component
public class CheckPermissionAop {
    @Resource
    private HttpServletRequest req;
    @Around("@annotation(com.xxx.annotation.PermissionProxy)")
    public Object checkPermission(ProceedingJoinPoint pjp){
        Object proceed=null;
//        检查当前是否登录
        Integer userIdFromCookie = LoginUserUtil.releaseUserIdFromCookie(req);
        if(userIdFromCookie==null){
            throw new NoLoginException();
        }
        List<Integer> permissions = (List<Integer>) req.getSession().getAttribute("permissions");//从session里拿出当前用户的权限码
        System.out.println(permissions.toString());

        try {
            MethodSignature signature= (MethodSignature) pjp.getSignature();//拿到方法签名
            System.out.println(signature.getName()+"<<<<<<被织入的方法名!");
            String code = signature.getMethod().getAnnotation(PermissionProxy.class).code();//通过方法签名拿到方法的对应注解的code(权限码)
            if(!(permissions.contains(Integer.parseInt(code)))){
                throw new PermissionException();
            }
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
