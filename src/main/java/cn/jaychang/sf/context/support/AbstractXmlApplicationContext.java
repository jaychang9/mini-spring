package cn.jaychang.sf.context.support;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.beans.factory.support.DefaultListableBeanFactory;
import cn.jaychang.sf.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author jaychang
 * @since 2023/12/19
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null && configLocations.length > 0) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
