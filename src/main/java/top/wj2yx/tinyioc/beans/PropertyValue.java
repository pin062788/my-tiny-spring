package top.wj2yx.tinyioc.beans;

/**
 * author: wang
 * date: 2018/5/5
 * time: 16:03
 */
public class PropertyValue {
    private final String name; // 属性名
    private final Object value; // 属性值

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
