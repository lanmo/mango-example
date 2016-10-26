package com.yn.mango.test;

import com.yn.mango.util.L;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yangnan on 16/10/26.
 * 获取泛型中的类型
 */
public class TypeTest {
    public static void main(String[] args) {
        B b = new B();
    }

    abstract static class A<T> {
    }

    static class B extends A<Integer> {
        public B() {
            Type type = getClass().getGenericSuperclass();
            if (!(type instanceof ParameterizedType)) {
                L.info("error");
            }

            L.info("type",type);

            Type[] ts = ((ParameterizedType)type).getActualTypeArguments();
            L.info("ts",ts);
            L.info("ts[0]",ts[0]);
        }
    }
}
