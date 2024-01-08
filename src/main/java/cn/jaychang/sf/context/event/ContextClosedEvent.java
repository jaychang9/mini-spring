package cn.jaychang.sf.context.event;

import cn.jaychang.sf.context.ApplicationContext;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class ContextClosedEvent extends ApplicationContextEvent {
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
