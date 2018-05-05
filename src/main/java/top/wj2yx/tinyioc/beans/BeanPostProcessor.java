package top.wj2yx.tinyioc.beans;

/**
 * author: wang
 * date: 2018/5/5
 * time: 17:06
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
