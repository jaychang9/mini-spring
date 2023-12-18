package cn.jaychang.sf.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.PropertyValue;
import cn.jaychang.sf.beans.factory.config.*;

import java.util.List;


/**
 * @author jaychang
 * @date 2023/12/11
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

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
        Object bean = null;
        try {
            bean = createInstance(beanDefinition);
            applyProperties(bean, beanDefinition);
            //执行 bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 创建完 bean 后，放到单例缓存中
        addSingleton(name, bean);
        return bean;
    }

    private void applyProperties(Object bean, BeanDefinition beanDefinition) {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            // 引用类型注入
            if (propertyValue.getValue() instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) propertyValue.getValue();
                Object referenceBean = getBean(beanReference.getBeanName());
                BeanUtil.setFieldValue(bean, propertyValue.getName(), referenceBean);
            } else {
                System.out.println(String.format("设置 bean 的 %s 属性值为 %s", propertyValue.getName(), propertyValue.getValue()));
                BeanUtil.setFieldValue(bean, propertyValue.getName(), propertyValue.getValue());
            }

        }
    }

    private Object createInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    public Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行 BeanPostProcessor 的前置处理
        Object wrappedBean = applyPostProcessBeforeInitialization(bean, beanName);

        // 执行 bean 的初始化方法
        invokeInitMethods(beanName, bean, beanDefinition);
        
        // 执行 BeanPostProcessor 的后置处理
        wrappedBean = applyPostProcessAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    /**
     * 执行 bean 初始化方法
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        System.out.println("AbstractAutowireCapableBeanFactory.invokeInitMethods");
    }


    public Object applyPostProcessBeforeInitialization(Object existingBean, String beanName) throws BeansException{
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessors();
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    public Object applyPostProcessAfterInitialization(Object existingBean, String beanName) throws BeansException{
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessors();
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessAfterInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
