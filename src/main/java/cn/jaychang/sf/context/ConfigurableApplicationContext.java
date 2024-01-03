package cn.jaychang.sf.context;

import cn.jaychang.sf.beans.BeansException;

/**
 * @author jaychang
 * @since 2023/12/19
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
