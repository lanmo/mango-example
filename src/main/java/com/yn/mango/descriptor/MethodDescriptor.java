package com.yn.mango.descriptor;

import com.yn.mango.annotation.DB;
import com.yn.mango.annotation.SQL;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by yangnan on 16/10/26.
 * 方法描述
 */
public class MethodDescriptor {
    private final Class<?> daoClass;
    private final List<ParameterDescriptor> parameterDescriptors;
    private final ReturnDescriptor returnDescriptor;

    public MethodDescriptor(Class<?> daoClass, List<ParameterDescriptor> parameterDescriptors, ReturnDescriptor
            returnDescriptor) {
        this.daoClass = daoClass;
        this.parameterDescriptors = parameterDescriptors;
        this.returnDescriptor = returnDescriptor;
    }

    public static MethodDescriptor create(Class<?> daoClass, List<ParameterDescriptor> parameterDescriptors, ReturnDescriptor
            returnDescriptor) {
        return new MethodDescriptor(daoClass, parameterDescriptors, returnDescriptor);
    }

    public String getSql() {
        SQL sql = getAnnotation(SQL.class);
        return sql.value();
    }

    public String getDatabase() {
        DB db = getAnnotation(DB.class);
        return db.database();
    }

    public <A extends Annotation> A getAnnotation(Class<A> annoType) {
        return returnDescriptor.getAnnotation(annoType);
    }

    public Class<?> getDaoClass() {
        return daoClass;
    }

    public List<ParameterDescriptor> getParameterDescriptors() {
        return parameterDescriptors;
    }
}
