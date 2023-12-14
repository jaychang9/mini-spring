package cn.jaychang.sf.beans.factory.config;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
