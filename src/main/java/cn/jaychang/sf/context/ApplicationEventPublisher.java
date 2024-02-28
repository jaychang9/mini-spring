package cn.jaychang.sf.context;

/**
 * 事件发布者接口
 */
public interface ApplicationEventPublisher {
    /**
     * 发布事件
     *
     * @param event 事件
     */
    void publishEvent(ApplicationEvent event);
}
