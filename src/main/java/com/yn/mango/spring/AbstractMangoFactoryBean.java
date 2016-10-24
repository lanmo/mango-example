package com.yn.mango.spring;

import com.yn.mango.operator.Mango;
import com.yn.mango.util.L;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by yangnan on 16/10/24.
 * 工厂bean
 */
public abstract class AbstractMangoFactoryBean implements FactoryBean {

    private Class<?> daoClass;
    private static volatile Mango mango;

    public abstract Mango createMango();

    public Object getObject() throws Exception {
        return getMango().create(daoClass);
    }

    public Class<?> getObjectType() {
        return daoClass;
    }

    public boolean isSingleton() {
        return true;
    }

    public Mango getMango() {

        if (mango == null) {
            synchronized (AbstractMangoFactoryBean.class) {
                if (mango == null) {
                    mango = createMango();
                }
            }
        }

        return mango;
    }

    public void setDaoClass(Class<?> daoClass) {
        this.daoClass = daoClass;
    }
}
