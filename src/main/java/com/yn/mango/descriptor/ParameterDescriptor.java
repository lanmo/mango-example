package com.yn.mango.descriptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yangnan on 16/10/26.
 * 方法参数描述
 */
public class ParameterDescriptor extends TypeWithAnnotationDescriptor {
    public ParameterDescriptor(Type type, Class<?> rawType, List<Annotation> annotations, TypeWrapper typeWrapper) {
//        super(type, rawType, annotations, typeWrapper);
        super(type, annotations);
    }
}
