package top.wj2yx.tinyioc.beans;

/**
 * author: wang
 * date: 2018/5/5
 * time: 16:00
 */
//对应于一个bean实例，通过封装可以添加一些元信息
public class BeanDefinition {
    private Object bean; //实例对象
    private Class beanClass; //对象所属类所对应的Class对象
    private String beanClassName; //类名
    private PropertyValues propertyValues = new PropertyValues();//属性键值对数组

    public BeanDefinition() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
