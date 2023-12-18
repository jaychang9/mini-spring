package cn.jaychang.sf.test.ioc;

import cn.hutool.core.lang.Assert;
import cn.jaychang.sf.beans.factory.support.DefaultListableBeanFactory;
import cn.jaychang.sf.beans.factory.xml.XmlBeanDefinitionReader;
import cn.jaychang.sf.core.io.DefaultResourceLoader;
import cn.jaychang.sf.test.bean.People;
import org.junit.Test;


/**
 * @author jaychang
 * @since 2023/12/15
 **/
public class XmlFileDefineBeanTest {
    @Test
    public void testXmlFile() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.setResourceLoader(new DefaultResourceLoader());
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        People people = (People) beanFactory.getBean("people");
        Assert.equals("jaychang", people.getName());
        Assert.equals("geely", people.getCar().getBrandName());

//        Map<String, People> peopleMap = beanFactory.getBeansOfType(People.class);
//        Assert.notEmpty(peopleMap,"peopleMap must not be empty");
//        for (Map.Entry<String, People> entry : peopleMap.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }


    }
}
