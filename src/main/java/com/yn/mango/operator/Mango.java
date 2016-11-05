package com.yn.mango.operator;

import com.yn.mango.cache.CacheLoader;
import com.yn.mango.cache.DoubleCache;
import com.yn.mango.cache.LoadingCache;
import com.yn.mango.datasource.DataSourceFactory;
import com.yn.mango.datasource.SimpleDataSourceFactory;
import com.yn.mango.descriptor.MethodDescriptor;
import com.yn.mango.descriptor.Methods;
import com.yn.mango.descriptor.ParameterNameDiscover;
import com.yn.mango.descriptor.SerialNumberParameterNameDiscover;
import com.yn.mango.jdbc.JdbcOptions;
import com.yn.mango.jdbc.JdbcTemplete;
import com.yn.mango.util.reflect.AbstractInvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.yn.mango.util.reflect.Reflection;
import org.apache.commons.collections4.ListUtils;

import javax.sql.DataSource;

/**
 * Created by yangnan on 16/10/24.
 *
 * 生成代理类
 */
public class Mango {

    private static List<Mango> instances = new ArrayList<Mango>();

    private DataSource dataSource;

    /**
     * 默认为 按参数顺序 定义参数名称
     */
    private ParameterNameDiscover parameterNameDiscover = new SerialNumberParameterNameDiscover();

    private JdbcOptions jdbcOperations = new JdbcTemplete();

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

        MangoInvocationHandler handler = new MangoInvocationHandler(this);
//        Method[] methods = daoClass.getMethods();
//        for (Method method : methods) {
//            handler.getOperator();
//        }

        return Reflection.newProxy(daoClass, handler);
    }

    private class MangoInvocationHandler extends AbstractInvocationHandler {

        private ParameterNameDiscover parameterNameDiscover;
        private final OperatorFactory operatorFactory;

        private final LoadingCache<Method, Operator> cache = new DoubleCache<Method, Operator>(new CacheLoader<Method, Operator>() {
            public Operator load(Method method) {
                MethodDescriptor md = Methods.getMethodDescriptor(method, parameterNameDiscover);
                return operatorFactory.getOperator(md);
            }
        });

        @Override
        public Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
            return getOperator(method).execute(args);
        }

        private MangoInvocationHandler(Mango mango) {
            this.parameterNameDiscover = mango.parameterNameDiscover;
            operatorFactory = new OperatorFactory(mango.dataSourceFactory, mango.jdbcOperations);
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
