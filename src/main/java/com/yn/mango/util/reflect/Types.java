package com.yn.mango.util.reflect;

import java.lang.reflect.Array;

/**
 * Created by yangnan on 16/10/26.
 */
public abstract class Types {
    static Class<?> getArrayClass(Class<?> componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }
}
