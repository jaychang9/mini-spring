package cn.jaychang.sf.context;

import java.util.EventListener;

/**
 * 事件监听者
 *
 * @param <E>
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
