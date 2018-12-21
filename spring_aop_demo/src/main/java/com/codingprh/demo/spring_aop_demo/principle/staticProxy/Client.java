package com.codingprh.demo.spring_aop_demo.principle.staticProxy;

import com.codingprh.demo.spring_aop_demo.principle.staticProxy.Proxy;
import com.codingprh.demo.spring_aop_demo.principle.staticProxy.RealSubject;
import com.codingprh.demo.spring_aop_demo.principle.staticProxy.Subject;

/**
 * 描述:
 * client端
 *
 * @author codingprh
 * @create 2018-12-21 3:05 PM
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new Proxy(new RealSubject());
        subject.request();


    }

}