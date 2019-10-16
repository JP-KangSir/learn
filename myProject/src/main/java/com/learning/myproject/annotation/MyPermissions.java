package com.learning.myproject.annotation;

import com.learning.myproject.enums.AclEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kjp
 * Data : 2019/4/23 0023
 * Time : 14:50
 * Desc : 自定义注解 实现权限控制
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyPermissions{

    AclEnum value();

}
