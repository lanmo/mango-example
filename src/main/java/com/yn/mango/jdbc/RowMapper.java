package com.yn.mango.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangnan on 16/11/5.
 */
public interface RowMapper<T> {
    
    Class<T> getMappedClass();

    T mapRow(ResultSet rs, int i) throws SQLException;
}
