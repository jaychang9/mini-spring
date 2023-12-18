package cn.jaychang.sf.beans.factory;

import cn.jaychang.sf.beans.factory.config.AutowireCapableBeanFactory;
import cn.jaychang.sf.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/15
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {
}
