package cn.jaychang.sf.context.event;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.BeanFactory;
import cn.jaychang.sf.beans.factory.BeanFactoryAware;
import cn.jaychang.sf.context.*;
import cn.jaychang.sf.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public abstract class AbstractApplicationListenerMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    protected Set<ApplicationListener<ApplicationEvent>> listeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        listeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        listeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
