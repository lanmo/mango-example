package com.yn.mango.operator;

import com.yn.mango.jdbc.DataSourceGenerator;
import com.yn.mango.jdbc.JdbcOptions;

/**
 * Created by yangnan on 16/10/24.
 * DB操作接口
 */
public interface Operator {
    Object execute(Object[] values);

    void setDataSourceGenerator(DataSourceGenerator dataSourceGenerator);

    void setJdbcOperations(JdbcOptions jdbcOperations);

    void setInvocationContextFactory(InvocationContextFactory invocationContextFactory);
}
