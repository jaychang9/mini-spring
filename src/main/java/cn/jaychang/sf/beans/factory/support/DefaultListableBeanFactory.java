package cn.jaychang.sf.beans.factory.support;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.ConfigurableListableBeanFactory;
import cn.jaychang.sf.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 含BeanDefinition 注册表
 *
 * @author jaychang
 * @date 2023/12/11
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }


    /**
     * AbstractBeanFactory抽象方法getBeanDefinition的实现
     *
     * @param name
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + name + "' is defined.");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {

        beanDefinitionMap.entrySet().stream().forEach(e -> {
            BeanDefinition beanDefinition = e.getValue();
            // 如果是非单例的不提前实例化
            if (beanDefinition.isPrototype()) {
                return;
            }
            // 是单例则提前实例化
            this.getBean(e.getKey());
        });
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> beansOfType) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            if (beansOfType.isAssignableFrom(beanDefinition.getBeanClass())) {
                T bean = (T) getBean(beanName);
                result.put(beanName, bean);
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNameSet = beanDefinitionMap.keySet();
        return beanNameSet.toArray(new String[beanNameSet.size()]);
    }

}
