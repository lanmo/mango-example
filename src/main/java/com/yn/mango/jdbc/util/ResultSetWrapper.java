package com.yn.mango.jdbc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangnan on 2016/12/6.
 */
public class ResultSetWrapper {

    private final ResultSet resultSet;

    private final List<String> columnNames = new ArrayList<String>();
    private List<Object> columnValues = new ArrayList<Object>();
    public ResultSetWrapper(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i=0; i<columnCount; i++) {
            String columnName = metaData.getColumnLabel(i);
            if (columnName == null || columnName.length() < 1) {
                columnName = metaData.getColumnName(i);
            }
            columnNames.add(columnName);
            columnValues.add(resultSet.getObject(i));
        }
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<Object> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<Object> columnValues) {
        this.columnValues = columnValues;
    }
}
