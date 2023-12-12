package cn.jaychang.sf.beans.factory.support;


import cn.jaychang.sf.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
