package com.yn.mango.descriptor;

import java.lang.reflect.Method;

/**
 * Created by yangnan on 16/11/5.
 * 根据序列化定义参数名字
 *
 */
public class SerialNumberParameterNameDiscover implements ParameterNameDiscover {
    public String[] getParameterNames(Method method) {
        String[] names = new String[method.getGenericParameterTypes().length];
        for (int i=0; i<names.length; i++) {
            names[i] = String.valueOf(i + 1);
        }
        return names;
    }
}
