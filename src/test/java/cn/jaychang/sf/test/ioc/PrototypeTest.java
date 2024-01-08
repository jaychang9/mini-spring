package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.context.ApplicationContext;
import cn.jaychang.sf.context.support.ClassPathXmlApplicationContext;
import cn.jaychang.sf.test.bean.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/1/5
 **/
public class PrototypeTest {
    @Test
    public void testPrototype() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car1 = (Car) applicationContext.getBean("car");
        Car car2 = (Car) applicationContext.getBean("car");

        Assert.assertTrue(car1 != car2);

    }
}
