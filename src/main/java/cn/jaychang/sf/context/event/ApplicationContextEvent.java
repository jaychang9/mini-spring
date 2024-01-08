package cn.jaychang.sf.context.event;

import cn.jaychang.sf.context.ApplicationContext;
import cn.jaychang.sf.context.ApplicationEvent;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public ApplicationContext getApplicationContext() {
        return (ApplicationContext)getSource();
    }

}
