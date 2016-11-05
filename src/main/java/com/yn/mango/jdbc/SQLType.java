package com.yn.mango.jdbc;

/**
 * Created by yangnan on 16/11/5.
 */
public enum SQLType {

    QUERY(true);

    private boolean needChangeData;

    private SQLType(boolean needChangeData) {
        this.needChangeData = needChangeData;
    }

    public void setNeedChangeData(boolean needChangeData) {
        this.needChangeData = needChangeData;
    }

    public boolean isNeedChangeData() {
        return needChangeData;
    }
}
