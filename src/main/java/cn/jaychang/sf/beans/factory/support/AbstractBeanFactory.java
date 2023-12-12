package cn.jaychang.sf.beans.factory.support;


import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.BeanFactory;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;

/**
 * @author jaychang
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        // 由于继承了 DefaultSingletonBeanRegistry 所以拥有了单例缓存的能力，因此这里可以先从单例缓存中取 bean
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        // 单例缓存中若没有 bean 再通过 getBeanDefinition 获取 BeanDefinition ，再调用抽象方法 createBean 创建 bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract Object createBean(String name, BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefinition(String name);

}
