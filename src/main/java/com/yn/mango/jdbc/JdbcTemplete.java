package com.yn.mango.jdbc;

import com.yn.mango.DataAccessException;
import org.jfaster.mango.binding.BoundSql;
import org.jfaster.mango.mapper.RowMapper;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/10/25.
 */
public class JdbcTemplete implements JdbcOptions {
    public <T> T queryForObject(DataSource ds, BoundSql boundSql, RowMapper<T> rowMapper) throws DataAccessException {
        return null;
    }
}
