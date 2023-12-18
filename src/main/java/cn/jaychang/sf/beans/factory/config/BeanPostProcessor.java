package cn.jaychang.sf.beans.factory.config;

import cn.jaychang.sf.beans.BeansException;


/**
 * 用于修改实例化后的bean的修改扩展点
 *
 * @author jaychang
 * @since 2023/12/18
 */
public interface BeanPostProcessor {
    /**
     * 在bean执行初始化方法之前执行此方法
     *
     * @param bean
     * @param beanName
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;


    /**
     * 在bean执行初始化方法之后执行此方法
     *
     * @param bean
     * @param beanName
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
