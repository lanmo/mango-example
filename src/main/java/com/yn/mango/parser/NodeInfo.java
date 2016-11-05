package com.yn.mango.parser;

import com.yn.mango.jdbc.SQLType;

/**
 * Created by yangnan on 16/11/5.
 */
public class NodeInfo {

    private SQLType sqlType;
    /**
     * 能执行的sql where id=?
     */
    private String exeSql;

    private int conditionCount;

    public NodeInfo(SQLType sqlType, String exeSql) {
        this.sqlType = sqlType;
        this.exeSql = exeSql;
    }

    public NodeInfo(SQLType sqlType, String exeSql, int conditionCount) {
        this.sqlType = sqlType;
        this.exeSql = exeSql;
        this.conditionCount = conditionCount;
    }

    public NodeInfo() {
    }

    public SQLType getSqlType() {
        return sqlType;
    }

    public void setSqlType(SQLType sqlType) {
        this.sqlType = sqlType;
    }

    public String getExeSql() {
        return exeSql;
    }

    public void setExeSql(String exeSql) {
        this.exeSql = exeSql;
    }

    public int getConditionCount() {
        return conditionCount;
    }

    public void setConditionCount(int conditionCount) {
        this.conditionCount = conditionCount;
    }
}
