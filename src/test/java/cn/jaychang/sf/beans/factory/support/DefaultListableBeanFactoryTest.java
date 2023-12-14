package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.PropertyValue;
import cn.jaychang.sf.beans.PropertyValues;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;
import cn.jaychang.sf.beans.factory.config.BeanReference;
import cn.jaychang.sf.test.bean.Car;
import cn.jaychang.sf.test.bean.People;
import org.junit.Assert;
import org.junit.Test;


public class DefaultListableBeanFactoryTest {

    @Test
    public void registerBeanDefinitionBySimpleInstantiation() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("helloService",new BeanDefinition(HelloService.class));
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        String result = helloService.sayHello();
        Assert.assertEquals(result,"hello");
    }

    @Test
    public void registerBeanDefinitionByCglibSubClassingInstantiation() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setInstantiationStrategy(new CglibSubclassingInstantiationStrategy());
        beanFactory.registerBeanDefinition("helloService",new BeanDefinition(HelloService.class));
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        String result = helloService.sayHello();
        Assert.assertEquals(result,"hello");
    }

    @Test
    public void testPopulateBeanWithPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "jaychang"));
        propertyValues.addPropertyValue(new PropertyValue("age", 18));
        beanFactory.registerBeanDefinition("people",new BeanDefinition(People.class, propertyValues));
        People people = (People) beanFactory.getBean("people");
        System.out.println(people);
        Assert.assertEquals(people.getName(),"jaychang");
    }

    @Test
    public void testPopulateBeanWithPropertyValuesHaveReference() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues peoplePropertyValues = new PropertyValues();
        peoplePropertyValues.addPropertyValue(new PropertyValue("name", "jaychang"));
        peoplePropertyValues.addPropertyValue(new PropertyValue("age", 18));
        peoplePropertyValues.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        beanFactory.registerBeanDefinition("people",new BeanDefinition(People.class, peoplePropertyValues));

        PropertyValues carPropertyValues = new PropertyValues();
        carPropertyValues.addPropertyValue(new PropertyValue("name","geely"));
        beanFactory.registerBeanDefinition("car",new BeanDefinition(Car.class, carPropertyValues));

        People people = (People) beanFactory.getBean("people");
        System.out.println(people);
        Assert.assertEquals(people.getName(),"jaychang");
        Assert.assertEquals(people.getCar().getName(),"geely");
    }


}