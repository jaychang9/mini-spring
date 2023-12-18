package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.beans.factory.support.DefaultListableBeanFactory;
import cn.jaychang.sf.beans.factory.xml.XmlBeanDefinitionReader;
import cn.jaychang.sf.test.bean.People;
import cn.jaychang.sf.test.common.CustomBeanFactoryPostProcessor;
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

    }
}
