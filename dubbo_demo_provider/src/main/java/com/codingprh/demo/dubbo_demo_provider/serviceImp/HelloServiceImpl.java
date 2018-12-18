package com.codingprh.demo.dubbo_demo_provider.serviceImp;


import com.alibaba.dubbo.config.annotation.Service;
import com.codingprh.demo.dubbo_demo_base.service.HelloService;

/**
 * 描述:
 * dubbo生产者提供的方法
 *
 * @author codingprh
 * @create 2018-12-17 9:53 AM
 */
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello dubbo");
        System.out.println("调用生产者的hello方法");
    }

    @Override
    public void sayWorld() {
        System.out.println("hello world");
    }
}