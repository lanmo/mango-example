package com.yn.mango.annotation;

import java.lang.annotation.*;

/**
 * Created by yangnan on 2016/12/6.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Result {
    /**
     * 数据库列
     */
    String column();

    /**
     * 对象属性
     */
    String property();
}
