package cn.jaychang.sf.aop;

import cn.jaychang.sf.aop.aspectj.AspectJExpressionPointcut;
import cn.jaychang.sf.aop.framework.AopProxy;
import cn.jaychang.sf.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

/**
 * @author jaychang
 * @since 2024/2/26
 **/
public class DynamicProxyTest {

    @Test
    public void test0() {
        WorldService worldService = new WorldServiceImpl();
        System.out.println(worldService.getClass().getInterfaces()[0].getName());
    }

    @Test
    public void test1() {
        WorldService worldService = new WorldServiceImpl();
        TargetSource targetSource = new TargetSource(worldService);

        AdvisedSupport advisedSupport = new AdvisedSupport(targetSource);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(new WorldServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.jaychang.sf.aop.WorldService.*(..))"));

        AopProxy aopProxy = new JdkDynamicAopProxy(advisedSupport);
        WorldService worldServiceProxy = (WorldService) aopProxy.getProxy();
        String explode = worldServiceProxy.explode();
        System.out.println(explode);

    }
}
