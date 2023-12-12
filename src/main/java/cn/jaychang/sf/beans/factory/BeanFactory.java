package cn.jaychang.sf.beans.factory;


import cn.jaychang.sf.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
