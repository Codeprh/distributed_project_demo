package com.codingprh.demo.spring_aop_demo.principle.cglibDynamicProxy;

import com.codingprh.demo.spring_aop_demo.principle.staticProxy.RealSubject;
import com.codingprh.demo.spring_aop_demo.principle.staticProxy.Subject;
import org.springframework.cglib.proxy.Enhancer;

/**
 * 描述:
 * 客户端
 *
 * @author codingprh
 * @create 2018-12-21 4:40 PM
 */
public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new CglibProxySubject());
        Subject subject = (Subject) enhancer.create();
        subject.sayHello();
    }

}