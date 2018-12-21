package com.codingprh.demo.spring_aop_demo.simpleAccessControl.service;

import com.codingprh.demo.spring_aop_demo.simpleAccessControl.model.CurrentUserHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author codingprh
 * @create 2018-12-20 5:21 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test(expected = Exception.class)
    public void insert() {
        CurrentUserHolder.setHolder("tom");
        productService.insert();
    }

    @Test
    public void insertAdmin() {
        CurrentUserHolder.setHolder("admin");
        productService.insert();
    }

    @Test
    public void inquire() {
        productService.inquire();
    }
}