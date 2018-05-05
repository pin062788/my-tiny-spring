package top.wj2yx.tinyioc;

/**
 * author: wang
 * date: 2018/5/5
 * time: 13:52
 */
//对应于属性中的bean引用
public class BeanReference {
    private String name; //bean的id名
    private Object bean; //bean实例

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
