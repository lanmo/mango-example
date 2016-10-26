package com.yn.mango.descriptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yangnan on 16/10/26.
 *
 * 方法参数
 */
public abstract class TypeWithAnnotationDescriptor {
    private final Type type;
    private final Class<?> rawType;
    private final List<Annotation> annotations;
//    private final TypeWrapper typeWrapper;

    public TypeWithAnnotationDescriptor(Type type, List<Annotation> annotations) {
        this.type = type;
        this.rawType = null;
        this.annotations = annotations;
//        this.typeWrapper = typeWrapper;
    }


}
