package cn.jaychang.sf.beans.factory.support;


import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.FactoryBean;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;
import cn.jaychang.sf.beans.factory.config.BeanPostProcessor;
import cn.jaychang.sf.beans.factory.config.ConfigurableBeanFactory;

import java.util.*;

/**
 * @author jaychang
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T)getBean(name);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        // 由于继承了 DefaultSingletonBeanRegistry 所以拥有了单例缓存的能力，因此这里可以先从单例缓存中取 bean
        Object sharedInstance  = getSingleton(name);
        if (sharedInstance  != null) {
            //如果是FactoryBean，从FactoryBean#getObject中创建bean
            return getObjectForBeanInstance(sharedInstance, name);
        }
        // 单例缓存中若没有 bean 再通过 getBeanDefinition 获取 BeanDefinition ，再调用抽象方法 createBean 创建 bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);
        return getObjectForBeanInstance(bean, name);
    }

    protected Object getObjectForBeanInstance(Object sharedInstance, String name) {
        Object bean = sharedInstance;
        if (!(sharedInstance instanceof FactoryBean)) {
            return bean;
        }
        FactoryBean factoryBean = (FactoryBean) sharedInstance;
        if (factoryBean.isSingleton()) {
            bean = factoryBeanObjectCache.get(name);
            if (Objects.isNull(bean)) {
                try {
                    bean =  factoryBean.getObject();
                    factoryBeanObjectCache.put(name, bean);
                } catch (Exception e) {
                    throw new BeansException(String.format("Error create bean use BeanFactory [%s], %s", name, e.getMessage()));
                }
            }
        } else {
            try {
                bean = factoryBean.getObject();
            } catch (Exception e) {
                throw new BeansException(String.format("Error create bean use BeanFactory [%s], %s", name, e.getMessage()));
            }
        }
        return bean;
    }

    protected abstract Object createBean(String name, BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String name);


    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
