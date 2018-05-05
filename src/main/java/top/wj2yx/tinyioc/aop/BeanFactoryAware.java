package top.wj2yx.tinyioc.aop;

import top.wj2yx.tinyioc.beans.factory.BeanFactory;

/**
 * author: wang
 * date: 2018/5/5
 * time: 18:13
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
