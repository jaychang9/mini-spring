package cn.jaychang.sf.context.support;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author jaychang
 * @since 2023/12/19
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    /**
     * 创建beanFactory并加载BeanDefinition
     *
     * @throws BeansException
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }


    protected DefaultListableBeanFactory createBeanFactory() {
        // DefaultListableBeanFactory 含 BeanDefinition 注册表
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;


    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
