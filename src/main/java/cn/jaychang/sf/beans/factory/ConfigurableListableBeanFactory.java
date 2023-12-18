package cn.jaychang.sf.beans.factory;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.config.AutowireCapableBeanFactory;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;
import cn.jaychang.sf.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/15
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    /**
     * 根据名称查找BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException 如果找不到BeanDefintion
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
