package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.core.io.Resource;
import cn.jaychang.sf.core.io.ResourceLoader;

/**
 * 读取 bean 定义信息即 BeanDefinition
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
