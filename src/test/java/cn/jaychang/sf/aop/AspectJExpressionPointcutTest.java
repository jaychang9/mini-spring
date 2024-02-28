package cn.jaychang.sf.aop;

import cn.jaychang.sf.aop.aspectj.AspectJExpressionPointcut;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author jaychang
 * @since 2024/1/16
 **/
public class AspectJExpressionPointcutTest {
    @Test
    public void testAspectJExpressionPointcut() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* cn.jaychang.sf.aop.HelloService.*(..))");
        Class<HelloService> helloServiceClass = HelloService.class;
        Method sayHelloMethod = helloServiceClass.getDeclaredMethod("sayHello", String.class);
        System.out.println(pointcut.matches(helloServiceClass));
        System.out.println(pointcut.matches(sayHelloMethod, helloServiceClass));
    }
}
