package com.codingprh.demo.spring_aop_demo.simpleAccessControl.model;

/**
 * 描述:
 * 当前线程所属用户
 *
 * @author codingprh
 * @create 2018-12-20 4:54 PM
 */
public class CurrentUserHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String getHolder() {
        return holder.get() == null ? "unkown" : holder.get();
    }

    public static void setHolder(String user) {
        holder.set(user);
    }

}