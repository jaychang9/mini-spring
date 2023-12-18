package cn.jaychang.sf.beans.factory.config;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改BeanDefinition的属性值
 *
 * @author jaychang
 * @since 2023/12/18
 **/
public interface BeanFactoryPostProcessor {
    /**
     * 在所有 BeanDefinition 加载完成后，但在 bean 实例化之前，提供修改 BeanDefinition 属性值的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
