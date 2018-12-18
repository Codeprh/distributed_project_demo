package com.codingprh.demo.dubbo_demo_base.service;

/**
 * @author codingprh
 * @create 2018-12-17 9:30 AM
 */
public interface HelloService {
    Integer VERSION = 1;

    /**
     * dubbo生产者接口的方法
     */
    void sayHello();

    void sayWorld();
}
