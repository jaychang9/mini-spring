package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object result = null;
        try {
            Constructor<?> constructor = beanClass.getConstructor(null);
            result = constructor.newInstance(null);
        } catch (Exception e ) {
            throw new RuntimeException(String.format("Bean [%s] instantiate error", beanDefinition.getBeanClass().getName()));
        }
        return result;
    }
}
