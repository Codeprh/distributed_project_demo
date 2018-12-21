package com.codingprh.demo.spring_aop_demo.principle.jdkDynamicProxy;

import com.codingprh.demo.spring_aop_demo.principle.staticProxy.RealSubject;
import com.codingprh.demo.spring_aop_demo.principle.staticProxy.Subject;

import java.lang.reflect.Proxy;

/**
 * 描述:
 * 客户端
 *
 * @author codingprh
 * @create 2018-12-21 3:50 PM
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
        subject.sayHello();
    }
}