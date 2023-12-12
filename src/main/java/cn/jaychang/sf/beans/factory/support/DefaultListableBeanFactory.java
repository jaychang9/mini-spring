package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 含BeanDefinition 注册表
 * @author jaychang
 * @date 2023/12/11
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }


    /**
     * AbstractBeanFactory抽象方法getBeanDefinition的实现
     *
     * @param name
     * @return
     */
    @Override
    protected BeanDefinition getBeanDefinition(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + name + "' is defined.");
        }
        return beanDefinition;
    }
}
