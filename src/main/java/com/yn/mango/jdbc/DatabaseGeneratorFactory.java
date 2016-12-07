package com.yn.mango.jdbc;

import com.yn.mango.binding.ParameterContext;

/**
 * Created by yangnan on 16/11/5.
 *
 * 数据源生成器工程
 */
public class DatabaseGeneratorFactory {

    public DataSourceGenerator getDataSourceGenerator(DataSourceType dataSourceType, String database) {
        return null;
    }

    public DatabaseGenerator getDatabaseGenerator(String database, ParameterContext parameterContext) {
        //TODO 先不要分库分表的策略
        DatabaseGenerator databaseGenerator = new SimpleDatabaseGenerator(database);
        return databaseGenerator;
    }
}
