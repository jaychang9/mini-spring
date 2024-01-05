package cn.jaychang.sf.beans.factory;

import cn.jaychang.sf.beans.BeansException;

import java.util.Map;

/**
 * Listable 前缀 顾名思义，具有列表能力的，这里指的是可以获取 所有 bean 定义的名称数组，以及获取指定 bean 类型的所有 bean 实例列表
 * @author jaychang
 * @description TODO
 * @date 2023/12/15
 **/
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 获取 beansOfType 类型的所有 bean 实例
     * @param beansOfType
     * @return
     */
    <T> Map<String,T> getBeansOfType(Class<T> beansOfType) throws BeansException;

    /**
     * 返回定义的所有 bean 的名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
