package cn.jaychang.sf.aop.framework.adapter;

import cn.jaychang.sf.aop.framework.MethodAfterReturningAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @author jaychang
 * @since 2024/2/28
 **/
public class MethodAfterReturningAdviceInterceptor implements MethodInterceptor {
    private MethodAfterReturningAdvice methodAfterReturningAdvice;

    public MethodAfterReturningAdviceInterceptor(MethodAfterReturningAdvice methodAfterReturningAdvice) {
        this.methodAfterReturningAdvice = methodAfterReturningAdvice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 在目标方法执行前执行
        Object result = invocation.proceed();
        methodAfterReturningAdvice.after(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return result;
    }
}
