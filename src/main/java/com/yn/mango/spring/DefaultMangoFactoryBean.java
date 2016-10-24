package com.yn.mango.spring;

import com.yn.mango.operator.Mango;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by yangnan on 16/10/24.
 * 默认工厂bean
 */
public class DefaultMangoFactoryBean extends AbstractMangoFactoryBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Mango createMango() {
        return applicationContext.getBean(Mango.class);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
