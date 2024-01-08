package cn.jaychang.sf.context.event;

import cn.jaychang.sf.context.ApplicationContext;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent {
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
