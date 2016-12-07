package com.yn.mango.jdbc;

import com.yn.mango.binding.InvocationContext;
import com.yn.mango.datasource.DataSourceFactory;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/11/8.
 */
public class DefaultDataSourceGenerator implements DataSourceGenerator {

    private final DatabaseGenerator databaseGenerator;
    private final DataSourceFactory dataSourceFactory;
    private final DataSourceType dataSourceType;

    public DefaultDataSourceGenerator(DatabaseGenerator databaseGenerator, DataSourceFactory dataSourceFactory,
                                      DataSourceType dataSourceType) {
        this.databaseGenerator = databaseGenerator;
        this.dataSourceFactory = dataSourceFactory;
        this.dataSourceType = dataSourceType;
    }


    public DataSource getDataSource(Class<?> daoClass) {

        String database = databaseGenerator.getDatabase();
        DataSource dataSource = dataSourceType == DataSourceType.MASTER ? dataSourceFactory.getMasterDataSource
                (database) : dataSourceFactory.getSlaveDataSource(database);

        return dataSource;
    }

    public DataSource getDataSource(InvocationContext context, Class<?> daoClass) {

        String database = databaseGenerator.getDatabase(context);
        DataSource ds = dataSourceType == DataSourceType.MASTER ? dataSourceFactory.getMasterDataSource(database) :
                dataSourceFactory.getSlaveDataSource(database);

        return ds;
    }
}
