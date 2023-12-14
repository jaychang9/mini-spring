package cn.jaychang.sf.beans;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
