package cn.jaychang.sf.test.common;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.PropertyValue;
import cn.jaychang.sf.beans.PropertyValues;
import cn.jaychang.sf.beans.factory.ConfigurableListableBeanFactory;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;
import cn.jaychang.sf.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author jaychang
 * @since 2023/12/18
 **/
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanFactoryPostProcessor.postProcessBeanFactory");
        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("people");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
        // 将 person 的名字属性改为 jack
        propertyValues.addPropertyValue(new PropertyValue("name", "jack"));

    }
}
