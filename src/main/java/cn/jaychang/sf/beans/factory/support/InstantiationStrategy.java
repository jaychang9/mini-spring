package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.factory.config.BeanDefinition;


public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition);
}
