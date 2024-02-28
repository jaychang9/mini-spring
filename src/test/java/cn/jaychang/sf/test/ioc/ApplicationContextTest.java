package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.context.ApplicationContext;
import cn.jaychang.sf.context.support.ClassPathXmlApplicationContext;
import cn.jaychang.sf.test.bean.Car;
import cn.jaychang.sf.test.bean.People;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/1/3
 **/
public class ApplicationContextTest {
    @Test
    public void testApplicationContext() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-for-test-application-context.xml");

        Car car = (Car) applicationContext.getBean("car");
        //brand属性在CustomerBeanPostProcessor中被修改为byd
        Assert.assertEquals("byd", car.getBrandName());

        // 每次获取 bean 时，都会调用各个注册在 spring 里的 BeanPostProcessor
        People people = (People) applicationContext.getBean("people");
        Assert.assertEquals("jack", people.getName());
    }
}
