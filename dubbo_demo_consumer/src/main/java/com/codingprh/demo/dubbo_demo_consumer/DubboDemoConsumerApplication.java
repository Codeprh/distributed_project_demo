package com.codingprh.demo.dubbo_demo_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.codingprh.demo.dubbo_demo_consumer.controller")
public class DubboDemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoConsumerApplication.class, args);
    }

}

