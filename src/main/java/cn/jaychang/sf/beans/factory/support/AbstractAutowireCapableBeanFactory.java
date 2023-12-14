package cn.jaychang.sf.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.jaychang.sf.beans.PropertyValue;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;
import cn.jaychang.sf.beans.factory.config.BeanReference;


/**
 * @author jaychang
 * @date 2023/12/11
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

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
        Object bean = createInstance(beanDefinition);
        applyFillProperties(bean, beanDefinition);
        // 创建完 bean 后，放到单例缓存中
        addSingleton(name, bean);
        return bean;
    }

    private void applyFillProperties(Object bean, BeanDefinition beanDefinition) {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            if (propertyValue.getValue() instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) propertyValue.getValue();
                Object referenceBean = getBean(beanReference.getBeanName());
                BeanUtil.setFieldValue(bean, propertyValue.getName(), referenceBean);
            } else {
                BeanUtil.setFieldValue(bean, propertyValue.getName(), propertyValue.getValue());
            }

        }
    }

    private Object createInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
