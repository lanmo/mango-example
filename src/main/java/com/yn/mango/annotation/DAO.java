package com.yn.mango.annotation;

import java.lang.annotation.*;

/**
 * Created by yangnan on 16/10/22.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DAO {
    /* 数据库配置名 */
    String database();
    /* 表名 */
    String table();
}
