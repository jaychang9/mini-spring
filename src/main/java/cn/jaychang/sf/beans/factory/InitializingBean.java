package cn.jaychang.sf.beans.factory;

/**
 * 初始化 Bean 接口
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
