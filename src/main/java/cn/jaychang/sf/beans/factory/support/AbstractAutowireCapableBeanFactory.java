package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;
/**
 * @author jaychang
 * @date 2023/12/11
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * AbstractBeanFactory抽象方法createBean的实现
     * @param name
     * @param beanDefinition
     * @return
     */
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) {
        return doCreateBean(name, beanDefinition);
    }

    protected Object doCreateBean(String name, BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = beanClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BeansException("Instantiation of bean failed", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 创建完 bean 后，放到单例缓存中
        addSingleton(name, bean);
        return bean;
    }


}
