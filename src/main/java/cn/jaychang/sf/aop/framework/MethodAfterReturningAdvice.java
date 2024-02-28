package cn.jaychang.sf.aop.framework;


import cn.jaychang.sf.aop.AfterReturning;

import java.lang.reflect.Method;

/**
 * @author jaychang
 * @since 2024/2/28
 **/
public interface MethodAfterReturningAdvice extends AfterReturning {
    void after(Method method, Object[] args, Object target) throws Throwable;
}
