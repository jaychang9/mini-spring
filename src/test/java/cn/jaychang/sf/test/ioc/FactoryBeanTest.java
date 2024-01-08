package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.context.ApplicationContext;
import cn.jaychang.sf.context.support.ClassPathXmlApplicationContext;
import cn.jaychang.sf.test.bean.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class FactoryBeanTest {
    @Test
    public void testFactoryBean() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        Assert.assertEquals("byd", car.getBrandName());
    }
}
