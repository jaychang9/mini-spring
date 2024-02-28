package cn.jaychang.sf.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author jaychang
 * @since 2024/1/16
 **/
public class AdvisedSupport {
    //是否使用cglib代理 (默认使用JDK动态代理)
    private boolean proxyTargetClass = false;

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public AdvisedSupport(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }
}
