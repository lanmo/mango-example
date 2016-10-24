package com.yn.mango.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yangnan on 16/10/24.
 * 代理工厂
 */
public abstract class AbstractInvocationHandler implements InvocationHandler {

    private final static Object[] NO_ARGS = {};

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (args == null) {
            args = NO_ARGS;
        }

        if (args.length == 0 && "hashCode".equals(method.getName())) {
            return hashCode();
        }

        if (args.length == 0 && "toString".equals(method.getName())) {
            return hashCode();
        }

        if (args.length == 1 && "equals".equals(method.getName()) && method.getParameterTypes()[0] == Object.class) {
            Object arg = args[0];
            return proxy.getClass().isInstance(arg) && equals(Proxy.getInvocationHandler(arg));
        }

        return handleInvocation(proxy, method, args);
    }

    public abstract Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable;
}
