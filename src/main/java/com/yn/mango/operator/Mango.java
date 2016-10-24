package com.yn.mango.operator;

import com.yn.mango.util.reflect.AbstractInvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.yn.mango.util.reflect.Reflection;

/**
 * Created by yangnan on 16/10/24.
 *
 * 生成代理类
 */
public class Mango {

    private static List<Mango> instances = new ArrayList<Mango>();

    public static Mango newInstance() {
        Mango mango = null;
        synchronized (Mango.class) {
            mango = new Mango();
            instances.add(mango);
        }

        return mango;
    }

    /**
     * 创建代理类
     * @param daoClass
     * @return
     */
    public <T> T create(Class<T> daoClass) {

        MangoInvocationHandler handler = new MangoInvocationHandler();
//        Method[] methods = daoClass.getMethods();
//        for (Method method : methods) {
//            handler.getOperator();
//        }

        return Reflection.newProxy(daoClass, handler);
    }

    private class MangoInvocationHandler extends AbstractInvocationHandler {

        @Override
        public Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
            return getOperator().execute(args);
        }

        private MangoInvocationHandler() {

        }

        private Operator getOperator() {
            return new QueryOperator();
        }
    }
}
