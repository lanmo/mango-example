package com.yn.mango.util.reflect;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by yangnan on 16/10/26.
 */
public class TypeWrapper {

    private Class<?> mappedClass;
    private Type mappedType;

    private boolean isArray;
    private boolean isCollection;
    private boolean isList;
    private boolean isArrayList;
    private boolean isLinkedList;
    private boolean isSet;
    private boolean isHashSet;
    private boolean isCollectionAssignable;

    public TypeWrapper(final Type type) {
        if (byte[].class.equals(type) || Byte[].class.equals(type)) {

        } else {
            new TypeVisitor() {
                @Override
                public void visitClass(Class<?> t) {
                    mappedType = t;
                    if (t.isArray()) {
                        mappedType = t.getComponentType();
                        isArray = true;
                    }
                }

                @Override
                public void visitGenericArrayType(GenericArrayType type) {
                    isArray = true;
                    mappedType = type.getGenericComponentType();
                }

                @Override
                public void visitParameterizedType(ParameterizedType type) {
                    Type rawType = type.getRawType();
                    // 支持Collection,List,ArrayList,LinkedList,Set,HashSet
                    if (Collection.class.equals(rawType)) {
                        isCollection = true;
                    } else if (List.class.equals(rawType)) {
                        isList = true;
                    } else if (ArrayList.class.equals(rawType)) {
                        isArrayList = true;
                    } else if (LinkedList.class.equals(rawType)) {
                        isLinkedList = true;
                    } else if (Set.class.equals(rawType)) {
                        isSet = true;
                    } else if (HashSet.class.equals(rawType)) {
                        isHashSet = true;
                    } else {
                        throw new IllegalStateException("parameterized type must be one of" +
                                "[Collection,List,ArrayList,LinkedList,Set,HashSet] but " + type);
                    }

                    isCollectionAssignable = true;
                    mappedType = type.getActualTypeArguments()[0];
                }

                @Override
                public void visitWildcardType(WildcardType type) {
                    new IllegalStateException("Does not support the type " + type);
                }

                @Override
                public void visitTypeVariable(TypeVariable<?> type) {
                    new IllegalStateException("Does not support the type " + type);
                }
            }.visit(type);
            mappedClass = TypeToken.of(mappedType).getRawType();
        }
    }

    public boolean isArray() {
        return isArray;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public boolean isList() {
        return isList;
    }

    public boolean isArrayList() {
        return isArrayList;
    }

    public boolean isLinkedList() {
        return isLinkedList;
    }

    public boolean isSet() {
        return isSet;
    }

    public boolean isHashSet() {
        return isHashSet;
    }

    public boolean isIterable() {
        return isCollectionAssignable || isArray;
    }

    public Class<?> getMappedClass() {
        return mappedClass;
    }

    public Type getMappedType() {
        return mappedType;
    }
}
