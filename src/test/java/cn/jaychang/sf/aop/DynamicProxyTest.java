package cn.jaychang.sf.aop;

import cn.jaychang.sf.aop.aspectj.AspectJExpressionPointcut;
import cn.jaychang.sf.aop.framework.AopProxy;
import cn.jaychang.sf.aop.framework.CglibAopProxy;
import cn.jaychang.sf.aop.framework.JdkDynamicAopProxy;
import cn.jaychang.sf.aop.framework.ProxyFactory;
import cn.jaychang.sf.aop.framework.adapter.MethodAfterReturningAdviceInterceptor;
import cn.jaychang.sf.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/2/26
 **/
public class DynamicProxyTest {
    private AdvisedSupport advisedSupport;

    @Before
    public void test0() {
        WorldService worldService = new WorldServiceImpl();
        TargetSource targetSource = new TargetSource(worldService);

        advisedSupport = new AdvisedSupport(targetSource);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(new WorldServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.jaychang.sf.aop.WorldService.*(..))"));
    }

    @Test
    public void testJdkDynamicProxy() {

        AopProxy aopProxy = new JdkDynamicAopProxy(advisedSupport);
        WorldService worldServiceProxy = (WorldService) aopProxy.getProxy();
        String explode = worldServiceProxy.explode();
        System.out.println(explode);

    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        String explode = proxy.explode();
        System.out.println(explode);
    }

    @Test
    public void testBeforeAdvice() throws Exception {
        WorldServiceBeforeAdvice worldServiceBeforeAdvice = new WorldServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(worldServiceBeforeAdvice);
        advisedSupport.setMethodInterceptor(methodBeforeAdviceInterceptor);

        ProxyFactory proxyFactory = new ProxyFactory(advisedSupport);
        WorldService worldService = (WorldService) proxyFactory.getProxy();
        worldService.explode();
    }

    @Test
    public void testAfterReturningAdvice() throws Exception {
        WorldServiceAfterReturning worldServiceAfterReturning = new WorldServiceAfterReturning();
        MethodAfterReturningAdviceInterceptor methodBeforeAdviceInterceptor = new MethodAfterReturningAdviceInterceptor(worldServiceAfterReturning);
        advisedSupport.setMethodInterceptor(methodBeforeAdviceInterceptor);

        ProxyFactory proxyFactory = new ProxyFactory(advisedSupport);
        WorldService worldService = (WorldService) proxyFactory.getProxy();
        worldService.explode();
    }

}