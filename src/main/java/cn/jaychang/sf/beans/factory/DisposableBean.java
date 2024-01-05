package cn.jaychang.sf.beans.factory;

/**
 * Bean 销毁前 资源释放处理
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
