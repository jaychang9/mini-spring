package cn.jaychang.sf.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author jaychang
 * @since 2024/2/26
 **/
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected final Object target;
    protected final Method method;
    protected final Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    public Object getThis() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
