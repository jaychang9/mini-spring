package cn.jaychang.sf.test.common;

import cn.jaychang.sf.context.ApplicationContext;
import cn.jaychang.sf.context.event.ApplicationContextEvent;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
