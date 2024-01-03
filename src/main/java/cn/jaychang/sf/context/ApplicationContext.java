package cn.jaychang.sf.context;

import cn.jaychang.sf.beans.factory.HierarchicalBeanFactory;
import cn.jaychang.sf.beans.factory.ListableBeanFactory;
import cn.jaychang.sf.core.io.ResourceLoader;

/**
 * 应用上下文
 * BeanFactory是spring的基础设施，面向spring本身；而ApplicationContext面向spring的使用者，应用场合使用ApplicationContext。
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
