package com.yn.mango.descriptor;

/**
 * Created by yangnan on 16/10/26.
 */
public class TypeWrapper {

    private Class<?> mappedClass;

    public boolean isArray() {
        return array;
    }

    public boolean isCollection() {
        return collection;
    }

    public boolean isList() {
        return list;
    }

    public boolean isArrayList() {
        return arrayList;
    }

    public boolean isLinkedList() {
        return linkedList;
    }

    public boolean isSet() {
        return set;
    }

    public boolean isHashSet() {
        return hashSet;
    }

    public boolean isIterable() {
        return iterable;
    }
}
