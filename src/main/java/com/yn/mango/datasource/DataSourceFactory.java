package com.yn.mango.datasource;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/10/25.
 * 数据工厂
 */
public interface DataSourceFactory {
    DataSource getMasterDataSource(String dataBase);
    DataSource getSlaveDataSource(String dataBase);
}
