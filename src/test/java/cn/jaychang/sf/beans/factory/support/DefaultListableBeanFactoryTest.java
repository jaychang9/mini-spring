package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.factory.config.BeanDefinition;
import org.junit.Assert;
import org.junit.Test;


public class DefaultListableBeanFactoryTest {

    @Test
    public void registerBeanDefinition() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("helloService",new BeanDefinition(HelloService.class));
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        String result = helloService.sayHello();
        Assert.assertEquals(result,"hello");
    }
}