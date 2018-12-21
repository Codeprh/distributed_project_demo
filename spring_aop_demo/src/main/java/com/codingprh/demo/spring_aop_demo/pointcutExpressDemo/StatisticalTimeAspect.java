package com.codingprh.demo.spring_aop_demo.pointcutExpressDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 统计方法时间耗时切面
 *
 * @author codingprh
 * @create 2018-12-21 11:11 AM
 */
@Aspect
@Component
public class StatisticalTimeAspect {
    /**
     * 统计在com.codingprh.demo.spring_aop_demo.simpleAccessControl.service
     * <p>
     * 包以及子包下，任何以Service结尾的类
     * <p>
     * 类中的任何以public开头的方法
     * <p>
     * 方法任意参数
     */
    @Pointcut("execution(public * com.codingprh.demo.spring_aop_demo.simpleAccessControl.service..*Service.*(..))")
    public void consumingTime() {

    }

    @Around("consumingTime()")
    public Object realOperator(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();
        System.out.println("调用方法：" + pjp.getSignature().getName() + "，startTime=" + startTime);
        System.out.println("相当于@Before");
        try {
            Object result = pjp.proceed(pjp.getArgs());
            System.out.println("相当于@AfterReturning");
            return result;
        } catch (Throwable throwable) {
            System.out.println("相当于@AfterThrowing");
            throw throwable;
        } finally {
            System.out.println("相当于@After");
            System.out.println("endTime,total=" + (System.currentTimeMillis() - startTime) + "ms");
        }
    }

}