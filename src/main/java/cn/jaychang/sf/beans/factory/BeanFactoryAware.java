package cn.jaychang.sf.beans.factory;

import cn.jaychang.sf.beans.BeansException;

/**
 * 实现该接口，能感知所属BeanFactory
 */
public interface BeanFactoryAware {
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
