package cn.jaychang.sf.aop;

import cn.jaychang.sf.aop.aspectj.AspectJExpressionPointcut;
import cn.jaychang.sf.aop.framework.ProxyFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/2/28
 **/
public class ProxyFactoryTest {

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
    public void testCreateProxyFactory() {
        // jdk 动态代理
        ProxyFactory proxyFactory = new ProxyFactory(advisedSupport);
        WorldService worldService = (WorldService) proxyFactory.getProxy();
        worldService.explode();

        // cglib 动态代理
        advisedSupport.setProxyTargetClass(true);
        worldService = (WorldService) proxyFactory.getProxy();
        worldService.explode();

    }
}
