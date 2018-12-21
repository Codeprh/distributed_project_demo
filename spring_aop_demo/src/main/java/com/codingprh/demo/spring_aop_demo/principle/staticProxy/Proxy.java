package com.codingprh.demo.spring_aop_demo.principle.staticProxy;

/**
 * 描述:
 * 代理对象
 *
 * @author codingprh
 * @create 2018-12-21 3:02 PM
 */
public class Proxy implements Subject {

    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public String request() {
        System.out.println("before hello proxy");
        realSubject.request();
        System.out.println("after hello proxy");
        return "hello proxy";
    }

    @Override
    public String sayHello() {
        return "";
    }
}