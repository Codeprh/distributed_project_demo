package com.codingprh.demo.spring_aop_demo.pointcutExpressDemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * pointcut匹配包类
 *
 * @author codingprh
 * @create 2018-12-21 9:33 AM
 */
@Aspect
@Component
public class ServiceAspect {
    /**
     * 拦截具体的某个具体的类
     */
//    @Pointcut("within(com.codingprh.demo.spring_aop_demo.simpleAccessControl.service.AuthService)")
//    public void matchClass() {
//
//    }

    /**
     * 拦截包，和包下面所有的类
     */
//    @Pointcut("within(com.codingprh.demo.spring_aop_demo.simpleAccessControl.service..*)")
//    public void matchPackageAndAll() {
//
//    }

    /**
     * 拦截以service结尾的bean里面的所有方法
     */
//    @Pointcut("bean(*Service)")
//    public void matchBean() {
//
//    }

    /**
     * 拦截所有insert开头的方法,并且只有一个参数的方法
     */
//    @Pointcut("execution(* *..insert*(Long))")
//    public void matchArgs() {
//
//    }

    /**
     * 匹配一个参数类型为String类型
     */
//    @Pointcut("args(String,..)")
//    public void matchArgs() {
//
//    }

    /**
     * 匹配方法&&使用execution表达式
     *
     * 匹配以public 开头，在com.codingprh.demo.spring_aop_demo.simpleAccessControl.service
     * 包以及子包，用*Service结尾的类的任意方法，任意参数
     */
    @Pointcut("execution(public * com.codingprh.demo.spring_aop_demo.simpleAccessControl.service..*Service.*(..))")
    public void matchMethod() {

    }

    @Before("matchMethod()")
    public void before() {
        System.out.println("service方法");
    }
}