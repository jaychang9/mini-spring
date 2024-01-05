package cn.jaychang.sf.beans.factory.support;

import cn.hutool.core.collection.CollectionUtil;
import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.DisposableBean;
import cn.jaychang.sf.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单例bean缓存
 *
 * @author jaychang
 * @date 2023/12/11
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonBeanRegistry;

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    public DefaultSingletonBeanRegistry() {
        this.singletonBeanRegistry = new HashMap<>();
    }

    @Override
    public Object getSingleton(String name) {
        return singletonBeanRegistry.get(name);
    }


    protected void addSingleton(String name, Object bean) {
        singletonBeanRegistry.put(name, bean);
    }

    public void registerDisposableBean(String name, DisposableBean disposableBean) {
        disposableBeans.put(name, disposableBean);
    }

    public void destroySingletons() {
        Set<String> beanNames = disposableBeans.keySet();
        if (CollectionUtil.isEmpty(beanNames)) {
            return;
        }
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' throw an exception", e);
            }
        }
    }
}
