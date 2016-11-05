package com.yn.mango.descriptor;

import java.lang.reflect.Method;

/**
 * Created by yangnan on 16/11/5.
 * 参数发现器
 *
 */
public interface ParameterNameDiscover {
    public String[] getParameterNames(Method method);
}
