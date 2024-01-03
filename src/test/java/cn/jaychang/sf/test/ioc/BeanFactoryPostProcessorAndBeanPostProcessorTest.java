package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.beans.factory.support.DefaultListableBeanFactory;
import cn.jaychang.sf.beans.factory.xml.XmlBeanDefinitionReader;
import cn.jaychang.sf.test.bean.Car;
import cn.jaychang.sf.test.bean.People;
import cn.jaychang.sf.test.common.CustomBeanFactoryPostProcessor;
import cn.jaychang.sf.test.common.CustomBeanPostProcessor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2023/12/18
 **/
public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

    @Test
    public void testBeanFactoryPostProcessor() throws Exception{
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //在所有BeanDefintion加载完成后，但在bean实例化之前，修改BeanDefinition的属性值
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(defaultListableBeanFactory);

        People people = (People) defaultListableBeanFactory.getBean("people");
        Assert.assertEquals("jack", people.getName());
    }

    @Test
    public void testBeanPostProcessor() throws Exception{
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanPostProcessor customBeanPostProcessor = new CustomBeanPostProcessor();
        defaultListableBeanFactory.addBeanPostProcessor(customBeanPostProcessor);

        Car car = (Car) defaultListableBeanFactory.getBean("car");
        //brand属性在CustomerBeanPostProcessor中被修改为byd
        Assert.assertEquals("byd",car.getBrandName());

        // 每次获取 bean 时，都会调用各个注册在 spring 里的 BeanPostProcessor
        People people = (People) defaultListableBeanFactory.getBean("people");
        Assert.assertEquals("jaychang", people.getName());
    }
}
