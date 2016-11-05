package com.yn.mango.descriptor;

import com.yn.mango.util.Objects;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yangnan on 16/11/5.
 */
public class ReturnDescriptor extends TypeWithAnnotationDescriptor {

    public ReturnDescriptor(Type type, List<Annotation> annotations) {
        super(type, annotations);
    }

    public static ReturnDescriptor create(Type type, List<Annotation> annotations) {
        return new ReturnDescriptor(type, annotations);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getType(), getAnnotations());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ReturnDescriptor other = (ReturnDescriptor) obj;
        return Objects.equals(this.getType(), other.getType())
                && Objects.equals(this.getAnnotations(), other.getAnnotations());
    }
}
