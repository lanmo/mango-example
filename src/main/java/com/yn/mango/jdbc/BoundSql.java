package com.yn.mango.jdbc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangnan on 16/11/5.
 */
public class BoundSql {
    private String sql;
    private final List<Object> args;

    public BoundSql(List<Object> args) {
        this.args = args;
    }

    public BoundSql(List<Object> args, String sql) {
        this.args = args;
        this.sql = sql;
    }

    public BoundSql(String sql) {
        this.sql = sql;
        this.args = new ArrayList<Object>();
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getArgs() {
        return args;
    }
}
