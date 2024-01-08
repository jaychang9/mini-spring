package cn.jaychang.sf.beans.factory.config;

/**
 * 单例注册表
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);

    void addSingleton(String beanName, Object singletonObject);

}
