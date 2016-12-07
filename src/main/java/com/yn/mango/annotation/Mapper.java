package com.yn.mango.annotation;

import com.yn.mango.jdbc.RowMapper;

import java.lang.annotation.*;

/**
 * Created by yangnan on 2016/12/6.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapper {
    Class<? extends RowMapper<?>> value();
}
