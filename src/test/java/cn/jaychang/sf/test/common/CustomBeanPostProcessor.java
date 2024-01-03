package cn.jaychang.sf.test.common;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.config.BeanPostProcessor;
import cn.jaychang.sf.test.bean.Car;

/**
 * @author jaychang
 * @since 2023/12/18
 **/
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor.postProcessBeforeInitialization,  beanName:" + beanName);
        if ("car".equals(beanName)) {
            Car car = (Car) bean;
            car.setBrandName("byd");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor.postProcessAfterInitialization");
        return bean;
    }
}
