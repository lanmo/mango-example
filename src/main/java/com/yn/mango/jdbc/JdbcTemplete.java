package com.yn.mango.jdbc;

import com.yn.mango.DataAccessException;
import com.yn.mango.annotation.Results;
import com.yn.mango.transaction.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangnan on 16/10/25.
 */
public class JdbcTemplete implements JdbcOptions {
    public <T> T queryForObject(DataSource ds, BoundSql boundSql, RowMapper<T> rowMapper) throws DataAccessException {
        return executeQuery(ds, boundSql, rowMapper, new ObjectResultSetExtractor<T>(rowMapper));
    }

    private <T> T executeQuery(DataSource ds, BoundSql boundSql, RowMapper<T> rowMapper, ResultSetExtractor<T> rse) {

        Connection conn = DataSourceUtils.getConnection(ds);
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = boundSql.getSql();
        try {
            ps = conn.prepareStatement(sql);
            setValues(ps, boundSql);
            rs = ps.executeQuery();
            return rse.extractData(rs);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(ps);
        }

        return null;
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setValues(PreparedStatement ps, BoundSql boundSql) throws SQLException {
        List<Object> args = boundSql.getArgs();
        for (int i=0; i<args.size(); i++) {
            ps.setObject(i, args.get(i));
        }
    }
}
