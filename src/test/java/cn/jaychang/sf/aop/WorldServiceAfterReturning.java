package cn.jaychang.sf.aop;


import cn.jaychang.sf.aop.framework.MethodAfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author jaychang
 * @since 2024/2/28
 **/
public class WorldServiceAfterReturning implements MethodAfterReturningAdvice {


    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("WorldServiceAfterReturning.after");
    }
}
