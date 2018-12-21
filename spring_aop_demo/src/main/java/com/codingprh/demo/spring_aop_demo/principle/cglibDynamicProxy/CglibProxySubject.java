package com.codingprh.demo.spring_aop_demo.principle.cglibDynamicProxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 描述:
 * 基于cglib实现的代理，采用继承方式
 *
 * @author codingprh
 * @create 2018-12-21 4:28 PM
 */
public class CglibProxySubject implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before in cglib");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(o, objects);
            System.out.println("result=" + result);
        } catch (Exception e) {
            System.out.println("get ex:" + e.getMessage());
            throw e;
        } finally {
            System.out.println("after in cglib");
        }
        return result;
    }
}