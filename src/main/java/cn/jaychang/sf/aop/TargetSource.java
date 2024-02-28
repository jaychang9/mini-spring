package cn.jaychang.sf.aop;

/**
 * 被代理的目标对象
 *
 * @author jaychang
 * @since 2024/1/16
 **/
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
