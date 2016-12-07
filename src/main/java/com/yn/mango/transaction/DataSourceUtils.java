package com.yn.mango.transaction;

import com.yn.mango.DataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yangnan on 2016/12/6.
 */
public class DataSourceUtils {

    public static Connection getConnection(DataSource dataSource) throws DataAccessException {
        try {
            return doGetConnection(dataSource);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private static Connection doGetConnection(DataSource dataSource) throws SQLException {

        Connection conn = dataSource.getConnection();
        try {
            if (!conn.getAutoCommit()) {
                conn.setAutoCommit(true);
            }
        } catch (Throwable e) {
            conn.close();
        }

        return conn;
    }
}
