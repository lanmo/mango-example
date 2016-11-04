package com.yn.mango.descriptor;

import com.yn.mango.util.Objects;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yangnan on 16/10/26.
 * 方法参数描述
 */
public class ParameterDescriptor extends TypeWithAnnotationDescriptor {

    /**
     * 此参数在method中的位置,默认从0开始
     */
    private final int position;
    /**
     * 此参数在method中的参数名
     */
    private final String name;

    public ParameterDescriptor(Type type, List<Annotation> annotations, int position, String name) {
        super(type, annotations);
        this.position = position;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public static ParameterDescriptor create(Type type, List<Annotation> annotations, int position, String name) {
        return new ParameterDescriptor(type, annotations, position, name);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParameterDescriptor that = (ParameterDescriptor) o;

        if (position != that.position) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position, name, getType(), getAnnotations());
    }
}
