package com.yn.mango.annotation;

import java.lang.annotation.*;

/**
 * Created by yangnan on 16/11/5.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SQL {

    String value();

}
