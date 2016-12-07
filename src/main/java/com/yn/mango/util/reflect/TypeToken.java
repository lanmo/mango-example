package com.yn.mango.util.reflect;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangnan on 16/10/26.
 *
 * 获取运行时参数的实际类型
 */
public abstract class TypeToken<T> extends TypeCapture<T> implements Serializable {
    /*实际运行类型*/
    private final Type runTimeType;
    /*不被序列化*/
    private transient TypeResolver resolver;

    public TypeToken() {
        this.runTimeType = capture();
    }

    public TypeToken(Type type) {
        this.runTimeType = type;
    }

    public static <T> TypeToken<T> of(Class<T> type) {
        return new SimpleTypeToken<T>(type);
    }

    public static TypeToken<?> of(Type type) {
        return new SimpleTypeToken<Object>(type);
    }

    public final Class<? extends T> getRawType() {
        Class<?> rawType = getRawType(this.runTimeType);
        return (Class<? extends T>) rawType;
    }

    private Class<?> getRawType(Type runTimeType) {
        return getRawTypes(runTimeType).iterator().next();
    }

    private Set<Class<?>> getRawTypes(final Type type) {
        final Set<Class<?>> types = new HashSet<Class<?>>();

        new TypeVisitor(){
            @Override
            public void visitTypeVariable(TypeVariable<?> t) {
                visit(t.getBounds());
            }

            @Override
            public void visitGenericArrayType(GenericArrayType t) {
                types.add(Types.getArrayClass(getRawType(t.getGenericComponentType())));
            }

            @Override
            public void visitClass(Class<?> t) {
                types.add(t);
            }

            @Override
            public void visitParameterizedType(ParameterizedType t) {
                types.add((Class<?>) t.getRawType());
            }

            @Override
            public void visitWildcardType(WildcardType t) {
                visit(t.getUpperBounds());
            }
        }.visit(type);

        return types;
    }

    private static final class SimpleTypeToken<T> extends TypeToken<T> {
        public SimpleTypeToken(Type type) {
            super(type);
        }
    }

}
