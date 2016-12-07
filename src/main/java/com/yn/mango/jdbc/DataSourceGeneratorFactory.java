package com.yn.mango.jdbc;

import com.yn.mango.binding.ParameterContext;
import com.yn.mango.datasource.DataSourceFactory;

/**
 * Created by yangnan on 16/11/5.
 */
public class DataSourceGeneratorFactory {

    private DataSourceFactory dataSourceFactory;
    private DatabaseGeneratorFactory databaseGeneratorFactory;

    public DataSourceGeneratorFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
        this.databaseGeneratorFactory = new DatabaseGeneratorFactory();
    }

    public DataSourceGenerator getDataSourceGenerator(DataSourceType dataSourceType, String database, ParameterContext parameterContext) {
        DatabaseGenerator databaseGenerator = databaseGeneratorFactory.getDatabaseGenerator(database, parameterContext);
        return new DefaultDataSourceGenerator(databaseGenerator, dataSourceFactory, dataSourceType);
    }
}
