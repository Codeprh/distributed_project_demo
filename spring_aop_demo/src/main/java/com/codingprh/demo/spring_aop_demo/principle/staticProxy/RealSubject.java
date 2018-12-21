package com.codingprh.demo.spring_aop_demo.principle.staticProxy;

/**
 * 描述:
 * 实现业务的对象
 *
 * @author codingprh
 * @create 2018-12-21 3:01 PM
 */
public class RealSubject implements Subject {

    @Override
    public String request() {
        System.out.println("hello world");
        return "hello world";
    }

    @Override
    public String sayHello() {
        System.out.println("hello world22");
        return "hello world22";
    }
}