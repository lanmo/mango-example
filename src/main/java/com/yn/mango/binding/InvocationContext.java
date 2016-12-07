package com.yn.mango.binding;

import com.yn.mango.jdbc.BoundSql;

/**
 * Created by yangnan on 2016/12/6.
 */
public interface InvocationContext {

    void addParameter(String parameterName, Object parameterValue);

    BoundSql getBoundSql();
    void writeToSqlBuffer(String str);
}
