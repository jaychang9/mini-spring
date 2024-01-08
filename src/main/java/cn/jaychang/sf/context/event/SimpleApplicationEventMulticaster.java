package cn.jaychang.sf.context.event;

import cn.jaychang.sf.beans.BeansException;
import cn.jaychang.sf.context.ApplicationEvent;
import cn.jaychang.sf.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author jaychang
 * @since 2024/1/8
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationListenerMulticaster{
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : listeners) {
            if (supportsEvent(listener, event)) {
                listener.onApplicationEvent(event);
            }
        }
    }

    /**
     * 监听器对此时间是否支持
     *
     * @param listener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener listener, ApplicationEvent event) {
        Type type = listener.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getClass().getTypeName();
        Class<?> eventClass = null;
        try {
            eventClass = Class.forName(className);
        } catch (Exception e) {
            throw new BeansException(String.format("Wrong event class name %s", className));
        }
        return eventClass.isAssignableFrom(event.getClass());
    }
}
