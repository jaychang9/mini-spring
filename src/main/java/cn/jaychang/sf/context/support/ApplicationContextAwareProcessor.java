package cn.jaychang.sf.context.support;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.config.BeanPostProcessor;
import cn.jaychang.sf.context.ApplicationContext;
import cn.jaychang.sf.context.ApplicationContextAware;

/**
 * @author jaychang
 * @since 2024/1/5
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ApplicationContextAware applicationContextAware = (ApplicationContextAware) bean;
            applicationContextAware.setApplicationContext(applicationContext);
        }
        return bean;
    }
}
