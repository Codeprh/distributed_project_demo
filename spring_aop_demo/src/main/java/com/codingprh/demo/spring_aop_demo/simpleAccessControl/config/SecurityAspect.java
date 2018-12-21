package com.codingprh.demo.spring_aop_demo.simpleAccessControl.config;

import com.codingprh.demo.spring_aop_demo.simpleAccessControl.annotation.AdminOnly;
import com.codingprh.demo.spring_aop_demo.simpleAccessControl.service.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 权限校验切面
 *
 * @author codingprh
 * @create 2018-12-20 4:25 PM
 */
@Component
@Aspect
public class SecurityAspect {
    @Autowired
    private AuthService authService;

    // 使用要拦截标注有AdminOnly的注解进行操作
    @Pointcut("@annotation(adminOnly)")
    public void adminOnlyMethod(AdminOnly adminOnly) {

    }

    @Before("adminOnlyMethod(adminOnly)")
    public void check(AdminOnly adminOnly) {
        authService.checkAccess();
    }


}