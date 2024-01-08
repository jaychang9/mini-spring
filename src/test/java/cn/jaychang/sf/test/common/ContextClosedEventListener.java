package cn.jaychang.sf.test.common;

import cn.jaychang.sf.context.ApplicationListener;
import cn.jaychang.sf.context.event.ContextClosedEvent;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("ContextClosedEventListener.onApplicationEvent");
    }
}
