package com.yn.mango.jdbc;

import com.yn.mango.binding.InvocationContext;

/**
 * Created by yangnan on 16/11/8.
 */
public interface DatabaseGenerator {

    /**
     * 获取数据库名称
     * @return
     */
    String getDatabase();

    String getDatabase(InvocationContext context);
}
