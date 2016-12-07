package com.yn.mango.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangnan on 2016/12/6.
 */
public interface ResultSetExtractor<T> {
    T extractData(ResultSet rs) throws SQLException;
}
