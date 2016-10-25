package com.yn.mango.operator;

import com.yn.mango.cache.CacheLoader;
import com.yn.mango.cache.DoubleCache;
import com.yn.mango.cache.LoadingCache;
import com.yn.mango.util.reflect.AbstractInvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.yn.mango.util.reflect.Reflection;
import org.apache.commons.collections4.ListUtils;
import org.jfaster.mango.datasource.DataSourceFactory;
import org.jfaster.mango.datasource.SimpleDataSourceFactory;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/10/24.
 *
 * 生成代理类
 */
public class Mango {

    private static List<Mango> instances = new ArrayList<Mango>();

    private DataSource dataSource;

    private DataSourceFactory dataSourceFactory;

    public static Mango newInstance() {
        Mango mango = null;
        synchronized (Mango.class) {
            mango = new Mango();
            instances.add(mango);
        }

        return mango;
    }

    /**
     * 设置数据源
     * @param dataSource
     * @return
     */
    public static Mango newInstance(DataSource dataSource) {
        return newInstance().setDataSource(dataSource);
    }

    public static Mango newInstance(DataSourceFactory dataSourceFactory) {
        return newInstance().setDataSourceFactory(dataSourceFactory);
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

        private final LoadingCache<Method, Operator> cache = new DoubleCache<Method, Operator>(new CacheLoader<Method, Operator>() {
            public Operator load(Method method) {
                return new QueryOperator();
            }
        });

        @Override
        public Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
            return getOperator(method).execute(args);
        }

        private MangoInvocationHandler() {

        }

        private Operator getOperator(Method md) {
            return cache.get(md);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Mango setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        if (dataSource != null) {
            setDataSourceFactory(new SimpleDataSourceFactory());
        }
        return this;
    }

    public List<Mango> getInstances() {
        return ListUtils.unmodifiableList(instances);
    }

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    public Mango setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
        return this;
    }
}
