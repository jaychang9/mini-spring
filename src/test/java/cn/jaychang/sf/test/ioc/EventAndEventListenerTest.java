package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class EventAndEventListenerTest {

    @Test
    public void testEventAndEventListener() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        applicationContext.registerShutdownHook();
    }
}
