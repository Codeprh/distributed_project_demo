package com.codingprh.demo.dubbo_demo_consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.codingprh.demo.dubbo_demo_base.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * hello控制器
 *
 * @author codingprh
 * @create 2018-12-17 10:23 AM
 */
@RestController
public class HelloController {
    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private HelloService helloService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        helloService.sayHello();
        return "hello consumer";
    }

}