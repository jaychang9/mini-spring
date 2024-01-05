package cn.jaychang.sf.test.ioc;

import cn.jaychang.sf.context.support.ClassPathXmlApplicationContext;
import cn.jaychang.sf.test.service.HelloService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jaychang
 * @since 2024/1/5
 **/
public class AwareInterfaceTest {

    @Test
    public void testAwareInterface() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aware-interface.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        assertThat(helloService.getApplicationContext()).isNotNull();
        assertThat(helloService.getBeanFactory()).isNotNull();


    }
}
