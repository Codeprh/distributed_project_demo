package com.codingprh.demo.spring_aop_demo.simpleAccessControl.service;

import com.codingprh.demo.spring_aop_demo.simpleAccessControl.annotation.AdminOnly;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 产品业务操作
 *
 * @author codingprh
 * @create 2018-12-20 5:16 PM
 */
@Service
public class ProductService {

    @AdminOnly
    public void insert() {
        System.out.println("插入一条数据成功");
    }
    
    public void inquire() {
        System.out.println("查询一条数据成功");
    }
}