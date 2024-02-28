package cn.jaychang.sf.aop.framework.adapter;

import cn.jaychang.sf.aop.framework.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author jaychang
 * @since 2024/2/28
 **/
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice methodBeforeAdvice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice methodBeforeAdvice) {
        this.methodBeforeAdvice = methodBeforeAdvice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 在目标方法执行前执行
        methodBeforeAdvice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
