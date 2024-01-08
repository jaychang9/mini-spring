package cn.jaychang.sf.test.common;

import cn.jaychang.sf.context.ApplicationListener;
import cn.jaychang.sf.context.event.ContextRefreshedEvent;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ContextRefreshedEventListener.onApplicationEvent");
    }
}
