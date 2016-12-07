package com.yn.mango.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangnan on 2016/12/6.
 */
public class ObjectResultSetExtractor<T> implements ResultSetExtractor<T> {

    private final RowMapper<T> rowMapper;

    public ObjectResultSetExtractor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    public T extractData(ResultSet rs) throws SQLException {

        Class<T> tClass = rowMapper.getMappedClass();

        T r = rowMapper.mapRow(rs, 0);
        if (r == null) {
            throw new RuntimeException("data is null");
        }

        return r;
    }
}
