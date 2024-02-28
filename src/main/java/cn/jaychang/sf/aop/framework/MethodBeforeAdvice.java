package cn.jaychang.sf.aop.framework;

import cn.jaychang.sf.aop.BeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author jaychang
 * @since 2024/2/28
 **/
public interface MethodBeforeAdvice extends BeforeAdvice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
