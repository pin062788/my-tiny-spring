package top.wj2yx.tinyioc.aop;

/**
 * 目标对象相关类
 * author: wang
 * date: 2018/5/5
 * time: 22:59
 */
public class TargetSource {
    /**
     * 目标对象所属的类
     */
    private Class<?> targetClass;

    /**
     * 目标对象所属类实现的接口数组
     */
    private Class<?>[] interfaces;

    /**
     * 目标对象本身
     */
    private Object target;

    public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces){
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTarget() {
        return target;
    }
}
