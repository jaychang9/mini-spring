package cn.jaychang.sf.context;

import cn.jaychang.sf.beans.BeansException;

/**
 * 实现该接口，能感知所属ApplicationContext
 */
public interface ApplicationContextAware {
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
