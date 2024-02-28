package cn.jaychang.sf.aop;

/**
 * 抽象切点
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
