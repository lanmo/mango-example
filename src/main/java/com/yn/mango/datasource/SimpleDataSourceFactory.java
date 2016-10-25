package com.yn.mango.datasource;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/10/25.
 */
public class SimpleDataSourceFactory implements DataSourceFactory {

    private DataSource dataSource;

    public SimpleDataSourceFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SimpleDataSourceFactory() {
    }

    public DataSource getMasterDataSource(String dataBase) {
        return dataSource;
    }

    public DataSource getSlaveDataSource(String dataBase) {
        return dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
