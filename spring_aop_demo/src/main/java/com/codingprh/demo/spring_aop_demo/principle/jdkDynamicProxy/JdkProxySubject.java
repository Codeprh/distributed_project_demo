package com.codingprh.demo.spring_aop_demo.principle.jdkDynamicProxy;

import com.codingprh.demo.spring_aop_demo.principle.staticProxy.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述:
 * 动态代理实现：基于jdk代理实现（接口）
 *
 * @author codingprh
 * @create 2018-12-21 3:35 PM
 */
public class JdkProxySubject implements InvocationHandler {

    private RealSubject realSubject;

    public JdkProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before");
        Object result = null;
        try {
            result = method.invoke(realSubject, args);
            System.out.println("result=" + result);
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("after");
        }

        return result;
    }
}