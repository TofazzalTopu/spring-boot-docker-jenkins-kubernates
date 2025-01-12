package com.info.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
 
@Component
public class ContextProvider implements ApplicationContextAware {
 
    private ApplicationContext applicationContext;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
 
    /**
     * Get a Spring bean by type.
     **/
    public <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }
 
    /**
     * Get a Spring bean by name.
     **/
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
 
}