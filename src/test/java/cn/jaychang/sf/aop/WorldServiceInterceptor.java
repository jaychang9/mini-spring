package cn.jaychang.sf.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author jaychang
 * @since 2024/2/26
 **/
public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before invocation");
        Object result = invocation.proceed();
        System.out.println("after invocation");
        return result;
    }
}
