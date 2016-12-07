package com.yn.mango.binding;

import com.yn.mango.jdbc.BoundSql;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangnan on 2016/12/6.
 */
public class DefaultInvocationContext implements InvocationContext {

    private Map<String, Object> parameterNameToValueMap = new LinkedHashMap<String, Object>();
    private List<Object> parameterValues = new ArrayList<Object>();
    private final StringBuilder sql = new StringBuilder();

    public void addParameter(String parameterName, Object parameterValue) {
        parameterNameToValueMap.put(parameterName, parameterValue);
        parameterValues.add(parameterValue);
    }



    public BoundSql getBoundSql() {
        return new BoundSql(parameterValues, sql.toString());
    }

    public void writeToSqlBuffer(String str) {
        sql.append(str);
    }

    public static DefaultInvocationContext create() {
        return new DefaultInvocationContext();
    }
}
