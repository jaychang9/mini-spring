package cn.jaychang.sf.aop;

import cn.jaychang.sf.aop.framework.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author jaychang
 * @since 2024/2/28
 **/
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("WorldServiceBeforeAdvice.before");
    }
}
