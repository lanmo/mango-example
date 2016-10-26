package com.yn.mango.util.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yangnan on 16/10/26.
 *
 * 获取泛型中的类型如
 * class A<T>
 * class B extents A<Integer></>
 * 此处返回值是java.lang.Integer
 */
abstract class TypeCapture<T> {
    public final Type capture() {
        Type superclass = getClass().getGenericSuperclass();
        if (!(superclass instanceof ParameterizedType)) {
            throw new IllegalArgumentException(superclass + " is not ParameterizedType");
        }

        /**例如
         * superclass:{
         *        "actualTypeArguments":["java.lang.Integer"],
         *        "ownerType":"com.yn.mango.test.TypeTest",
         *        "rawType":"com.yn.mango.test.TypeTest$A"
         *    }
         */
        return ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }
}
