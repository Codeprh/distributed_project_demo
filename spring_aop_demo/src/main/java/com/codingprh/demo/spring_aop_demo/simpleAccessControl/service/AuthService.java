package com.codingprh.demo.spring_aop_demo.simpleAccessControl.service;

import com.codingprh.demo.spring_aop_demo.simpleAccessControl.model.CurrentUserHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 描述:
 * 权限控制业务
 *
 * @author codingprh
 * @create 2018-12-20 4:48 PM
 */
@Service
public class AuthService {

    public void checkAccess() {
        String user = CurrentUserHolder.getHolder();
        if (!Objects.equals("admin", user)) {
            throw new RuntimeException("当前用户没有权限操作！！！");
        }
    }

}