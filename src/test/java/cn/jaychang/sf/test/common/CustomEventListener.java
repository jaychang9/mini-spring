package cn.jaychang.sf.test.common;

import cn.jaychang.sf.context.ApplicationListener;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("CustomEventListener.onApplicationEvent");
    }
}
