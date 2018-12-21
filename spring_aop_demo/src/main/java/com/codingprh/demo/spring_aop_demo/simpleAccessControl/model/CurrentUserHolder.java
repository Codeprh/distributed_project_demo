package com.codingprh.demo.spring_aop_demo.simpleAccessControl.model;

import org.springframework.stereotype.Component;

/**
 * 描述:
 * 当前线程所属用户
 *
 * @author codingprh
 * @create 2018-12-20 4:54 PM
 */
@Component
public class CurrentUserHolder {
    private static ThreadLocal<String> holder = new ThreadLocal<>();

    public String getHolder() {
        return holder.get() == null ? "unkown" : holder.get();
    }

    public void setHolder(String user) {
        holder.set(user);
    }

}