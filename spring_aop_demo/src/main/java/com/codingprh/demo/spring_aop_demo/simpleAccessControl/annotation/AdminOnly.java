package com.codingprh.demo.spring_aop_demo.simpleAccessControl.annotation;

/**
 * @author codingprh
 * @create 2018-12-20 4:13 PM
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdminOnly {
}
