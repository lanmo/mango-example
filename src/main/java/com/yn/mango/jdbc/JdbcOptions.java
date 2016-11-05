package com.yn.mango.jdbc;

import com.yn.mango.DataAccessException;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/10/25.
 */
public interface JdbcOptions {
    public <T> T queryForObject(DataSource ds, BoundSql boundSql, RowMapper<T> rowMapper)
            throws DataAccessException;
}
