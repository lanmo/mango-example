package com.yn.mango.operator;

import com.yn.mango.DataAccessException;
import com.yn.mango.jdbc.RowMapper;
import com.yn.mango.jdbc.util.ResultSetWrapper;
import com.yn.mango.util.reflect.Types;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * Created by yangnan on 2016/12/6.
 */
public class BeanPropertyRowMapper<T> implements RowMapper<T> {

    private Class<T> mappedClass;
    private Map<String, String> ptc;

    public BeanPropertyRowMapper(Class<T> mappedClass, Map<String, String> ptc) {
        this.mappedClass = mappedClass;
        this.ptc = ptc;
    }

    public Class<T> getMappedClass() {
        return mappedClass;
    }

    public T mapRow(ResultSet rs, int rowNum) throws SQLException {

        T mappedObject = instantiate(mappedClass);

        ResultSetWrapper wrapper = new ResultSetWrapper(rs);
        rs.next();
        int columnCount = wrapper.getColumnCount();
        for (int i=0; i<columnCount; i++) {
            String columnName = getSetMethodName(wrapper.getColumnNames().get(i));
            invokeSet(mappedObject, columnName, rs.getObject(wrapper.getColumnNames().get(i)));
        }

        return mappedObject;
    }

    /**
     *
     * @param setMethodName
     */
    public void invokeSet(T t, String setMethodName, Object args) {
        try {
            if (args instanceof java.sql.Timestamp) {
                java.sql.Timestamp args1 = (java.sql.Timestamp)args;
                args = new Date(args1.getTime());
            }
            mappedClass.getMethod(setMethodName, args.getClass()).invoke(t, args);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 生成set方法
     *
     * @param property
     * @return
     */
    private String getSetMethodName(String property) {
        String[] strs = property.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder("set");
        for (String str : strs) {
            sb.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
        }

        return sb.toString();
    }

    private T instantiate(Class<T> tClass) {
        if (tClass.isInterface()) {
            System.out.println(tClass + "' is interface");
        }
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
