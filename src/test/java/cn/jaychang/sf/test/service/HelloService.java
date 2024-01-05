package cn.jaychang.sf.test.service;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.BeanFactory;
import cn.jaychang.sf.beans.factory.BeanFactoryAware;
import cn.jaychang.sf.context.ApplicationContext;
import cn.jaychang.sf.context.ApplicationContextAware;

/**
 * @author jaychang
 * @since 2024/1/5
 **/
public class HelloService implements BeanFactoryAware, ApplicationContextAware {

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
