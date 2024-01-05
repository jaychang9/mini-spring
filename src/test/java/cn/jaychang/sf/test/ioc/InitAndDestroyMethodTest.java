package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.context.support.ClassPathXmlApplicationContext;
import cn.jaychang.sf.test.bean.Car;
import cn.jaychang.sf.test.bean.People;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/1/5
 **/
public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();

        Car car = (Car) applicationContext.getBean("car");

        // 每次获取 bean 时，都会调用各个注册在 spring 里的 BeanPostProcessor
        People people = (People) applicationContext.getBean("people");


    }
}
