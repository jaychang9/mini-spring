package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例bean缓存
 *
 * @author jaychang
 * @date 2023/12/11
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonBeanRegistry;

    public DefaultSingletonBeanRegistry() {
        this.singletonBeanRegistry = new HashMap<>();
    }

    @Override
    public Object getSingleton(String name) {
        return singletonBeanRegistry.get(name);
    }


    protected void addSingleton(String name , Object bean) {
        singletonBeanRegistry.put(name,bean);
    }
}
