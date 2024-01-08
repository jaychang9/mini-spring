package cn.jaychang.sf.context.support;

import cn.hutool.core.collection.CollectionUtil;
import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.ConfigurableListableBeanFactory;
import cn.jaychang.sf.beans.factory.config.BeanFactoryPostProcessor;
import cn.jaychang.sf.beans.factory.config.BeanPostProcessor;
import cn.jaychang.sf.context.ApplicationEvent;
import cn.jaychang.sf.context.ApplicationListener;
import cn.jaychang.sf.context.ConfigurableApplicationContext;
import cn.jaychang.sf.context.event.ApplicationEventMulticaster;
import cn.jaychang.sf.context.event.ContextClosedEvent;
import cn.jaychang.sf.context.event.ContextRefreshedEvent;
import cn.jaychang.sf.context.event.SimpleApplicationEventMulticaster;
import cn.jaychang.sf.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author jaychang
 * @since 2023/12/19
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";


    private ApplicationEventMulticaster applicationEventMulticaster;


    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory ，并加载 BeanDefinition
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 增加内置的 ApplicationContextAwareProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 在 bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 注册 BeanPostProcessor， 需要在 bean 实例化之前注册
        registerBeanPostProcessors(beanFactory);

        //初始化事件发布者
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListeners();

        // 提前实例化单例bean
        beanFactory.preInstantiateSingletons();

        // 发布容器刷新完成事件
        finishRefresh();

    }


    protected void initApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 注册内置的单例事件发布者
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, simpleApplicationEventMulticaster);
        this.applicationEventMulticaster = simpleApplicationEventMulticaster;
    }

    protected void registerListeners() {
        Map<String, ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class);
        if (CollectionUtil.isEmpty(applicationListeners)) {
            return;
        }
        for (ApplicationListener applicationListener : applicationListeners.values()) {
            this.applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }


    protected void finishRefresh() {
        publicEvent(new ContextRefreshedEvent(this));
    }

    protected void publicEvent(ApplicationEvent applicationEvent) {
        this.applicationEventMulticaster.multicastEvent(applicationEvent);
    }


    /**
     * 在 bean 实例化之前，执行 BeanFactoryPostProcessor
     *
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        // 所有 BeanFactoryPostProcessor 实例也放 spring 容器里了
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册 BeanPostProcessor， 需要在 bean 实例化之前注册
     *
     * @param beanFactory
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> beansOfType) throws BeansException {
        return getBeanFactory().getBeansOfType(beansOfType);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBeanFactory().getBean(name);
    }

    /**
     * 获取BeanFactory
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 创建BeanFactory，并加载BeanDefinition
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    public void close() {
        doClose();
    }

    public void registerShutdownHook() {
        Thread shutdownHook = new Thread() {
            public void run() {
                doClose();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    protected void doClose() {
        // 发布容器关闭事件
        publicEvent(new ContextClosedEvent(this));

        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
