package cn.jaychang.sf.context.event;

import cn.jaychang.sf.context.ApplicationEvent;
import cn.jaychang.sf.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> event);

    void removeApplicationListener(ApplicationListener<?> event);

    void multicastEvent(ApplicationEvent event);
}
