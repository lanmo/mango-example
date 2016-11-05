package com.yn.mango.util.reflect;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangnan on 16/10/26.
 */
public abstract class TypeVisitor {

    private final Set<Type> visited = new HashSet<Type>();

    /**
     * 查看给定的type 访问给定的type
     * @param types
     */
    public final void visit(Type... types) {
        for (Type type : types) {
            if (type == null || !visited.add(type)) {
                //ownertype 或者已经被访问
                continue;
            }

            boolean isSuccess = false;
            try {
                if (type instanceof TypeVariable<?>) {
                    visitTypeVariable((TypeVariable<?>)type);
                } else if (type instanceof WildcardType) {
                    visitWildcardType((WildcardType)type);
                } else if (type instanceof ParameterizedType) {
                    visitParameterizedType((ParameterizedType)type);
                } else if (type instanceof Class<?>) {
                    visitClassType((Class<?>)type);
                } else if (type instanceof GenericArrayType) {
                    visitGenericArrayType((GenericArrayType)type);
                } else {
                    throw new IllegalArgumentException("type error " + type);
                }
                isSuccess = true;
            } finally {
                if (isSuccess) {
                    visited.remove(type);
                }
            }

        }
    }

    public void visitClass(Class<?> t) {
    }

    public void visitGenericArrayType(GenericArrayType type) {
    }

    public void visitClassType(Class<?> type) {
    }

    public void visitParameterizedType(ParameterizedType type) {
    }

    public void visitWildcardType(WildcardType type) {
    }

    public void visitTypeVariable(TypeVariable<?> type) {
    }
}
