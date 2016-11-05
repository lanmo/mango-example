package com.yn.mango.test;

import com.yn.mango.util.L;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangnan on 16/10/26.
 * 获取泛型中的类型
 */
public class TypeTest {
    public static void main(String[] args) {
        B b = new B();
        List<String> s = new ArrayList<String>();
        Type a = s.getClass().getGenericSuperclass();
        ParameterizedType y = (ParameterizedType) a;
        L.info(s.getClass().getTypeParameters());
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
