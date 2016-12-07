package com.yn.mango.jdbc;

import com.yn.mango.binding.InvocationContext;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/11/5.
 * 数据源生成器
 */
public interface DataSourceGenerator {

    /**
     * 获取数据源
     *
     * @param daoClass
     * @return
     */
    DataSource getDataSource(Class<?> daoClass);

    DataSource getDataSource(InvocationContext context, Class<?> daoClass);
}
