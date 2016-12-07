package com.yn.mango.descriptor;

import com.yn.mango.util.reflect.TypeWrapper;

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
    private TypeWrapper typeWrapper;

    public TypeWithAnnotationDescriptor(Type type, List<Annotation> annotations) {
        this.type = type;
        this.rawType = null;
        this.annotations = annotations;
        this.typeWrapper = new TypeWrapper(type);
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return getAnnotation(annotationType) == null;
    }

    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        for (Annotation annotation : getAnnotations()) {
            if (annotationType.isInstance(annotation)) {
                return annotationType.cast(annotation);
            }
        }
        return null;
    }

    public Type getType() {
        return type;
    }

    public Class<?> getRawType() {
        return rawType;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public boolean isArray() {
        return typeWrapper.isArray();
    }

    public boolean isCollection() {
        return typeWrapper.isCollection();
    }

    public boolean isList() {
        return typeWrapper.isList();
    }

    public boolean isArrayList() {
        return typeWrapper.isArrayList();
    }

    public boolean isLinkedList() {
        return typeWrapper.isLinkedList();
    }

    public boolean isSet() {
        return typeWrapper.isSet();
    }

    public boolean isHashSet() {
        return typeWrapper.isHashSet();
    }

    public boolean isIterable() {
        return typeWrapper.isIterable();
    }

    public boolean isListAssignable() {
        return isList() || isArrayList() || isLinkedList();
    }

    public boolean isSetAssignable() {
        return isSet() || isHashSet();
    }

    public Class<?> getMappedClass() {
        return typeWrapper.getMappedClass();
    }

}
